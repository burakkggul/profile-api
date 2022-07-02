package tr.com.burakgul.profileapi.model.entity.main;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "resume")
@Getter
@Setter
@NoArgsConstructor
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "work_experience_title")
    private String workExperienceTitle;

    @OneToMany
    @JoinColumn(name = "resume_id")
    private List<WorkExperience> workExperiences;

    @Column(name = "education_title")
    private String educationTitle;

    @OneToMany
    @JoinColumn(name = "resume_id")
    private List<Education> educations;
}
