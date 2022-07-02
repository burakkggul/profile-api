package tr.com.burakgul.profileapi.repository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.burakgul.profileapi.model.entity.blog.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
