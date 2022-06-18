package tr.com.burakgul.profileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.burakgul.profileapi.model.entity.Header;

import java.util.Optional;

@Repository
public interface HeaderRepository extends JpaRepository<Header,Long> {

    Optional<Header> findTopByOrderByIdDesc();
}
