package tr.com.burakgul.profileapi.repository.main;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.main.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.main.About;

import java.util.Optional;


public interface ResumeRepository extends JpaRepository<Resume,Long> {
    Optional<Resume> findTopByOrderByIdDesc();
}
