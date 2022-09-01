package tr.com.burakgul.profileapi.model.dto.main;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SkillDTO {
    private Integer progress;
    private String name;
}
