package tr.com.burakgul.profileapi.service.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectHistoryUtil;
import tr.com.burakgul.profileapi.model.dto.blog.CommentRequest;
import tr.com.burakgul.profileapi.model.dto.blog.CommentResponse;
import tr.com.burakgul.profileapi.model.entity.User;
import tr.com.burakgul.profileapi.model.entity.blog.Clap;
import tr.com.burakgul.profileapi.model.entity.blog.Comment;
import tr.com.burakgul.profileapi.model.entity.blog.Post;
import tr.com.burakgul.profileapi.repository.blog.ClapRepository;
import tr.com.burakgul.profileapi.repository.blog.CommentRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ClapRepository clapRepository;
    private final PostService postService;
    private final DTOMapper dtoMapper;

    @Transactional(readOnly = true)
    public List<CommentResponse> findByPostId(Long postId) {
        List<Comment> comments = this.commentRepository.findByPost_Id(postId);
        if (!comments.isEmpty()) {
            return comments.stream().map(comment -> {
                CommentResponse commentResponse = this.dtoMapper.mapModel(comment, CommentResponse.class);
                commentResponse.setClapCount(comment.getClaps().size());
                return commentResponse;
            }).collect(Collectors.toList());

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bu postId ile comment bulunamadı.");
        }
    }

    @Transactional
    public CommentResponse save(CommentRequest commentRequest) {
        Optional<Post> postOptional = this.postService.findById(commentRequest.getPostId());
        if (postOptional.isPresent()) {
            Comment comment = this.dtoMapper.mapModel(commentRequest, Comment.class);
            comment.setPost(postOptional.get());
            //FIXME Sadece test için şimdilik.
            User user = new User();
            user.setId(1L);
            comment.setCreatedBy(user);
            // ***************
            ObjectHistoryUtil.initHistoricalEntity(comment);
            Comment savedComment = this.commentRepository.save(comment);
            CommentResponse commentResponse = this.dtoMapper.mapModel(savedComment, CommentResponse.class);
            commentResponse.setClapCount(savedComment.getClaps().size());
            return commentResponse;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Postid ile post bulunamadı.");
        }
    }

    @Transactional
    public CommentResponse update(CommentRequest commentRequest, Long commentId) {
        Optional<Comment> commentOptional = this.commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment savedComment = commentOptional.get();
            savedComment.setContent(commentRequest.getContent());
            ObjectHistoryUtil.setLastModifiedDate(savedComment);
            Comment upToDateComment = this.commentRepository.save(savedComment);
            CommentResponse commentResponse = this.dtoMapper.mapModel(upToDateComment, CommentResponse.class);
            commentResponse.setClapCount(upToDateComment.getClaps().size());
            return commentResponse;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Yorum bulunamadı.");
        }
    }

    @Transactional
    public CommentResponse increaseCommentClapByCommentId(Long commentId) {
        Optional<Comment> commentOptional = this.commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            // Sadece test için şimdilik.
            // FIXME cascade'de problem var.
            User user = new User();
            user.setId(1L);
            // **************************
            Comment comment = commentOptional.get();

            List<Clap> findExistingClap = comment.getClaps().stream()
                    .filter(clap -> clap.getCreatedBy().getId().equals(user.getId()))
                    .collect(Collectors.toList());

            if (findExistingClap.isEmpty()) {
                return this.addClapToComment(user, comment);
            } else {
                return this.removeClapFromComment(findExistingClap, comment);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bu id ile comment bulunamadı.");
        }
    }

    @Transactional
    public CommentResponse addClapToComment(User user, Comment comment) {
        Clap clap = new Clap();
        clap.setCreatedDate(new Date());
        clap.setCreatedBy(user);
        Clap savedClap = this.clapRepository.save(clap);
        comment.getClaps().add(savedClap);
        Comment upToDateComment = this.commentRepository.save(comment);
        CommentResponse commentResponse = this.dtoMapper
                .mapModel(upToDateComment, CommentResponse.class);
        commentResponse.setClapCount(upToDateComment.getClaps().size());
        return commentResponse;
    }

    @Transactional
    public CommentResponse removeClapFromComment(List<Clap> existingClaps,
                                                 Comment comment) {
        comment.getClaps().removeAll(existingClaps);
        Comment upToDateComment = this.commentRepository.save(comment);
        this.clapRepository.deleteAll(existingClaps);
        CommentResponse commentResponse = this.dtoMapper
                .mapModel(upToDateComment, CommentResponse.class);
        commentResponse.setClapCount(upToDateComment.getClaps().size());
        return commentResponse;
    }
}
