package tr.com.burakgul.profileapi.controller.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burakgul.profileapi.model.dto.blog.CommentRequest;
import tr.com.burakgul.profileapi.model.dto.blog.CommentResponse;
import tr.com.burakgul.profileapi.service.blog.CommentService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentResponse>> findByPostId(@RequestParam Long postId){
        return ResponseEntity.ok(this.commentService.findByPostId(postId));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> save(@RequestBody CommentRequest commentRequest){
        return ResponseEntity.created(URI.create("/comment"))
                .body(this.commentService.save(commentRequest));
    }

    @PutMapping
    public ResponseEntity<CommentResponse> update(@RequestBody CommentRequest commentRequest,
                                                  @RequestParam Long commentId){
        return ResponseEntity.ok(this.commentService.update(commentRequest,commentId));
    }

    @PutMapping("/clap")
    public ResponseEntity<CommentResponse> increaseCommentClapByCommentId(@RequestParam Long commentId){
        return ResponseEntity.ok(this.commentService.increaseCommentClapByCommentId(commentId));
    }
}
