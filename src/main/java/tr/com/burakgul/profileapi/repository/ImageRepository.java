package tr.com.burakgul.profileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.Image;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
