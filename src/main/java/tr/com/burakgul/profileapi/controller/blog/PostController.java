package tr.com.burakgul.profileapi.controller.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.model.dto.blog.PostRequest;
import tr.com.burakgul.profileapi.model.dto.blog.PostResponse;
import tr.com.burakgul.profileapi.service.blog.PostService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> save(@RequestBody PostRequest postRequest){
        return ResponseEntity.created(URI.create("/post"))
                .body(this.postService.save(postRequest));
    }

    @PutMapping
    public ResponseEntity<PostResponse> update(@RequestBody PostRequest postRequest, @RequestParam Long postId){
        return ResponseEntity.ok(this.postService.update(postRequest, postId));
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> findByUsername(@RequestParam String username){
        return ResponseEntity.ok(this.postService.findByUsername(username));
    }

    @PutMapping("/clap")
    public ResponseEntity<PostResponse> increasePostClapByPostId(@RequestParam Long postId){
        return ResponseEntity.ok(this.postService.increasePostClapByPostId(postId));
    }
}
