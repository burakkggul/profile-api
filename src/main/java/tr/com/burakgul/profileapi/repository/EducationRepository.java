package tr.com.burakgul.profileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.Education;

public interface EducationRepository extends JpaRepository<Education,Long> {
}
