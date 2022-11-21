package tr.com.burakgul.profileapi.repository.main;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.main.SkillSection;

import java.util.Optional;

public interface SkillSectionRepository extends JpaRepository<SkillSection,Long> {
    Optional<SkillSection> findTopByOrderByIdDesc();
}
