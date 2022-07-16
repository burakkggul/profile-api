package tr.com.burakgul.profileapi.service.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectHistoryUtil;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.core.util.PostUtil;
import tr.com.burakgul.profileapi.model.dto.blog.PostRequest;
import tr.com.burakgul.profileapi.model.dto.blog.PostResponse;
import tr.com.burakgul.profileapi.model.entity.User;
import tr.com.burakgul.profileapi.model.entity.blog.Clap;
import tr.com.burakgul.profileapi.model.entity.blog.Post;
import tr.com.burakgul.profileapi.repository.blog.ClapRepository;
import tr.com.burakgul.profileapi.repository.blog.PostRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final ClapRepository clapRepository;


    private final DTOMapper dtoMapper;

    @Transactional(readOnly = true)
    public List<PostResponse> findByUsername(String username) {
        List<Post> posts = this.postRepository.findByCreatedBy_Username(username);
        return posts.stream().map(post -> {
            PostResponse postResponse = this.dtoMapper.mapModel(post,PostResponse.class);
            PostUtil.calculateAndSetCommentAndClapCount(post,postResponse);
            return postResponse;
        }).collect(Collectors.toList());
    }

    @Transactional
    public PostResponse save(PostRequest postRequest) {
        Post postToSave = this.dtoMapper.mapModel(postRequest,Post.class);
        ObjectHistoryUtil.initHistoricalEntity(postToSave);
        PostUtil.calculateAndSetReadingMinute(postToSave);
        //FIXME Sadece test için şimdilik.
        User user = new User();
        user.setId(1L);
        postToSave.setCreatedBy(user);
        //*********************************
        Post savedPost = this.postRepository.save(postToSave);
        PostResponse postResponse = this.dtoMapper.mapModel(savedPost,PostResponse.class);
        PostUtil.calculateAndSetCommentAndClapCount(savedPost,postResponse);
        return postResponse;
    }

    @Transactional
    public PostResponse update(PostRequest postRequest, Long postId) {
        Optional<Post> savedPostOptional = this.postRepository.findById(postId);
        if(savedPostOptional.isPresent()){
            Post savedPost = savedPostOptional.get();
            Post upToDatePost = this.dtoMapper.mapModel(postRequest,Post.class);
            ObjectUpdaterUtil.updateObject(savedPost,upToDatePost,
                    Arrays.asList("id","createdBy","readingMinute","comments","claps", "createdDate","lastModifiedDate"));
            ObjectHistoryUtil.setLastModifiedDate(savedPost);
            PostUtil.calculateAndSetReadingMinute(savedPost);
            Post upToDateSavedPost = this.postRepository.save(savedPost);
            PostResponse postResponse = this.dtoMapper.mapModel(upToDateSavedPost,PostResponse.class);
            PostUtil.calculateAndSetCommentAndClapCount(upToDatePost,postResponse);
            return postResponse;
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Bu id ile post bulunamadı.");
        }
    }

    //TODO Bu metodu refactor edelim. Ödev yapılırken increase ve decrease ayrı metotlar ve endpointler olacak.
    @Transactional
    public PostResponse increasePostClapByPostId(Long postId) {
        Optional<Post> optionalPost = this.postRepository.findById(postId);
        if(optionalPost.isPresent()){
            //Sadece test için şimdilik.
            //FIXME cascade'de problem var.
            User user = new User();
            user.setId(1L);
            Post post = optionalPost.get();
            Post savedPost;
            Optional<Clap> clapOptional = this.clapRepository.findByCreatedBy_IdAndPost_Id(user.getId(),postId);
            if(!clapOptional.isPresent()){
                //Clap ekli değilse ekle.
                Clap clap = new Clap();
                clap.setCreatedDate(new Date());
                clap.setCreatedBy(user);

                Clap savedClap = this.clapRepository.save(clap);
                post.getClaps().add(savedClap);
                savedPost = this.postRepository.save(post);

            }else {
                // Clap zaten eklenmişse kaldır.
                post.getClaps().removeIf(clap -> clap.getId().equals(clapOptional.get().getId()));
                this.clapRepository.delete(clapOptional.get());
                savedPost = this.postRepository.save(post);
            }
            PostResponse postResponse = this.dtoMapper.mapModel(savedPost,PostResponse.class);
            PostUtil.calculateAndSetCommentAndClapCount(savedPost,postResponse);
            return postResponse;
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Bu id ile post bulunamadı.");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Post> findById(Long id){
        return this.postRepository.findById(id);
    }
}
