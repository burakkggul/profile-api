package tr.com.burakgul.profileapi.model.dto.blog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.burakgul.profileapi.model.dto.UserResponse;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private Date createdDate;
    private UserResponse createdBy;
    private Long readingMinute;
    private String title;
    private String content;
    private Integer commentCount;
    private Integer clapCount;
    private List<CategoryResponse> categories;
}
