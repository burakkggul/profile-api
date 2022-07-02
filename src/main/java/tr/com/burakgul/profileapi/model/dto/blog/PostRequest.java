package tr.com.burakgul.profileapi.model.dto.blog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String content;
    private List<CategoryResponse> categories;
}
