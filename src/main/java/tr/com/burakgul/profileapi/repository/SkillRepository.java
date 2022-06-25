package tr.com.burakgul.profileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill,Long> {
}
