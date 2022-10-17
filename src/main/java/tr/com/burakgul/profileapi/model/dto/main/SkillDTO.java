package tr.com.burakgul.profileapi.model.dto.main;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SkillDTO {
    private Integer progress;
    private String name;
}
