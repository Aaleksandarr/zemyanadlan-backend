package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.PlaceListing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceListingRepository extends JpaRepository<PlaceListing, Long> {

    Page<PlaceListing> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<PlaceListing> findAllByUser_IdOrderByCreatedAtDesc(Long userId, Pageable pageable);

    Page<PlaceListing> findAllByCategories_SlugOrderByCreatedAtDesc(String slug, Pageable pageable);
}
