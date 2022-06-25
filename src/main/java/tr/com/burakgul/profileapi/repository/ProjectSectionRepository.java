package tr.com.burakgul.profileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.ProjectSection;

public interface ProjectSectionRepository extends JpaRepository<ProjectSection,Long> {
}
