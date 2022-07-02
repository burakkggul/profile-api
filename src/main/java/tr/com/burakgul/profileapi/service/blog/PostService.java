package tr.com.burakgul.profileapi.service.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.model.dto.blog.PostRequest;
import tr.com.burakgul.profileapi.model.dto.blog.PostResponse;
import tr.com.burakgul.profileapi.repository.blog.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;



    public List<PostResponse> findByUsername(String username) {
        return new ArrayList<>();
    }

    public PostResponse save(PostRequest postRequest) {
        return new PostResponse();
    }

    public PostResponse update(PostRequest postRequest) {
        return new PostResponse();
    }

    public PostResponse increasePostClapByPostId(Long postId) {
        return new PostResponse();
    }
}
