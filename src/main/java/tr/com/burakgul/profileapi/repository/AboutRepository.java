package tr.com.burakgul.profileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.About;

import java.util.Optional;

public interface AboutRepository extends JpaRepository<About,Long> {
    // select * from order by id desc limit 1;
    Optional<About> findTopByOrderByIdDesc();
}
