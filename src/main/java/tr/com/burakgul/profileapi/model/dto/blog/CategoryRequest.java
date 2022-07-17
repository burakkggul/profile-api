package tr.com.burakgul.profileapi.model.dto.blog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Bu category eklemek düzenlemek ve silmek için kullanılacak bir modeldir.")
public class CategoryRequest {
    @ApiModelProperty(name = "test-property",value = "test-property",required = true)
    private String title;
    private String description;
}
