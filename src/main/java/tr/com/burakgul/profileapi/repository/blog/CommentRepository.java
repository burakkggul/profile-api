package tr.com.burakgul.profileapi.repository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.com.burakgul.profileapi.model.entity.blog.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("select C from Comment C where C.post.id=:postId")
    List<Comment> findByPost_Id(Long postId);
}
