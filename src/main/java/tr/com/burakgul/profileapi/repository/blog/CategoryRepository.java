package tr.com.burakgul.profileapi.repository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.burakgul.profileapi.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
