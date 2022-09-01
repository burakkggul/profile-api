package tr.com.burakgul.profileapi.model.dto.main;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class SkillSectionDTO {
    private String title;
    private List<SkillDTO> skills;
}
