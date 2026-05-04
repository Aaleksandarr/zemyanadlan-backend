package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.EventListing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventListingRepository extends JpaRepository<EventListing, Long> {

    Page<EventListing> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<EventListing> findAllByUser_IdOrderByCreatedAtDesc(Long userId, Pageable pageable);

    Page<EventListing> findAllByCategories_slugOrderByCreatedAtDesc(String slug, Pageable pageable);
}
