package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.MarketListing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketListingRepository extends JpaRepository<MarketListing, Long> {
    Page<MarketListing> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<MarketListing> findAllByUser_IdOrderByCreatedAtDesc(Long userId, Pageable pageable);

    Page<MarketListing> findAllByCategories_SlugOrderByCreatedAtDesc(String slug, Pageable pageable);
}
