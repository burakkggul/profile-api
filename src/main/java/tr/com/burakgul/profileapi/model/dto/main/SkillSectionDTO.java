package tr.com.burakgul.profileapi.model.dto.main;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SkillSectionDTO {
    private String title;
    private List<SkillDTO> skills;
}
