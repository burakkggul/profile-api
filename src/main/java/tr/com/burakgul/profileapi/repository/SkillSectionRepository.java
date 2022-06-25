package tr.com.burakgul.profileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.SkillSection;

public interface SkillSectionRepository extends JpaRepository<SkillSection,Long> {
}
