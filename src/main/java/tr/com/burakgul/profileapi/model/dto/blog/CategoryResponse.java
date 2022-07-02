package tr.com.burakgul.profileapi.model.dto.blog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponse {
    private Long id;
    private String title;
    private String description;
}
