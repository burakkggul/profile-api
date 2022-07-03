package tr.com.burakgul.profileapi.repository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.blog.Clap;

import java.util.Optional;

public interface ClapRepository extends JpaRepository<Clap,Long> {
    Optional<Clap> findByCreatedBy_IdAndPost_Id(Long createdById, Long postId);
}
