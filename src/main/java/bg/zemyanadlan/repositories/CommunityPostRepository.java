package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.CommunityPost;
import bg.zemyanadlan.entities.PostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
    Page<CommunityPost> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<CommunityPost> findByPostTypeOrderByCreatedAtDesc(
            String postType,
            Pageable pageable
    );
}
