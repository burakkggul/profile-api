package tr.com.burakgul.profileapi.repository.main;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.main.ProjectSection;

import java.util.Optional;

public interface ProjectSectionRepository extends JpaRepository<ProjectSection,Long> {
    Optional<ProjectSection> findTopByOrderByIdDesc();
}
