package tr.com.burakgul.profileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.burakgul.profileapi.model.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
