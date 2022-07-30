package tr.com.burakgul.profileapi.controller.blog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
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
import tr.com.burakgul.profileapi.auth.TokenManager;
import tr.com.burakgul.profileapi.model.dto.blog.CategoryResponse;
import tr.com.burakgul.profileapi.model.dto.blog.CategoryRequest;
import tr.com.burakgul.profileapi.service.blog.CategoryService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog/category")
@Api(description = "Category API")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        return ResponseEntity.ok(this.categoryService.findAll());
    }

    @ApiOperation(value = "update categories", notes = "Bunu dikkatli kullan.",httpMethod = "GET")
    @PutMapping
    public @ApiResponse(code = 300, message = "Bu bir test mesajıdır.")
    ResponseEntity<CategoryResponse> update(@RequestBody
                                            @ApiParam(name = "categoryResponse",
                                                    required = true,
                                                    readOnly = true) CategoryResponse categoryResponse) {
        return ResponseEntity.ok(this.categoryService.update(categoryResponse));
    }


    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.created(URI.create("/blog/category"))
                .body(this.categoryService.save(categoryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        this.categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
