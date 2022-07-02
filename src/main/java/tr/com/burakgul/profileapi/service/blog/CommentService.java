package tr.com.burakgul.profileapi.service.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.model.dto.blog.CommentRequest;
import tr.com.burakgul.profileapi.model.dto.blog.CommentResponse;
import tr.com.burakgul.profileapi.repository.blog.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<CommentResponse> findByPostId(Long postId) {
        return new ArrayList<>();
    }

    public CommentResponse save(CommentRequest commentRequest) {
        return new CommentResponse();
    }

    public CommentResponse update(CommentRequest commentRequest) {
        return new CommentResponse();
    }

    public CommentResponse increaseCommentClapByCommentId(Long commentId) {
        return new CommentResponse();
    }
}
