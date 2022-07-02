package tr.com.burakgul.profileapi.controller.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burakgul.profileapi.model.dto.blog.CategoryResponse;
import tr.com.burakgul.profileapi.model.dto.blog.CategoryRequest;
import tr.com.burakgul.profileapi.service.blog.CategoryService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        return ResponseEntity.ok(this.categoryService.findAll());
    }

    @PutMapping
    public ResponseEntity<CategoryResponse> update(@RequestBody CategoryResponse categoryResponse) {
        return ResponseEntity.ok(this.categoryService.update(categoryResponse));
    }


    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.created(URI.create("/blog/category"))
                .body(this.categoryService.save(categoryRequest));
    }

    //TODO soft delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        this.categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
