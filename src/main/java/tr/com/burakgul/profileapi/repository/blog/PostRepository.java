package tr.com.burakgul.profileapi.repository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.burakgul.profileapi.model.entity.blog.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByCreatedBy_Username(String username);
}
