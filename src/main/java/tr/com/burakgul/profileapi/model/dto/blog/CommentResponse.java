package tr.com.burakgul.profileapi.model.dto.blog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.burakgul.profileapi.model.dto.UserResponse;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String content;
    private UserResponse createdBy;
    private Integer clapCount;
}
