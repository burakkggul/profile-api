package tr.com.burakgul.profileapi.model.dto.main;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResumeDTO {
    private String title;
    private String workExperienceTitle;
    private List<WorkExperienceDTO> workExperiences;
    private String educationTitle;
    private List<EducationDTO> educations;
}
