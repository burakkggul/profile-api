package tr.com.burakgul.profileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume,Long> {
}
