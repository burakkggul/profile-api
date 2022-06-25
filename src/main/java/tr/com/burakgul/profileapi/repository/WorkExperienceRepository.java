package tr.com.burakgul.profileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.WorkExperience;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience,Long> {
}
