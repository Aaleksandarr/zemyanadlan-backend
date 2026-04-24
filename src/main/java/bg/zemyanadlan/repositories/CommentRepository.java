package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
