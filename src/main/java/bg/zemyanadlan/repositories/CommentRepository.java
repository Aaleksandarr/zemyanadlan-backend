package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.Comment;
import bg.zemyanadlan.entities.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostOrderByCreatedAtAsc(CommunityPost post);
}
