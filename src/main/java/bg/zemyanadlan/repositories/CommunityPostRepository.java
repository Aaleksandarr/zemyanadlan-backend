package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
}
