package tr.com.burakgul.profileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.Project;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
