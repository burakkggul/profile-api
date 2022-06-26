package tr.com.burakgul.profileapi.repository.main;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.SocialMedia;

public interface SocialMediaRepository extends JpaRepository<SocialMedia,Long> {
}
