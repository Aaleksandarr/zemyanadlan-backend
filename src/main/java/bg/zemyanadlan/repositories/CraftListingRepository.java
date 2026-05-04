package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.CraftListing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CraftListingRepository extends JpaRepository<CraftListing, Long> {
    Page<CraftListing> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<CraftListing> findAllByCategories_SlugOrderByCreatedAtDesc(String slug, Pageable pageable);

    Page<CraftListing> findAllByUser_IdOrderByCreatedAtDesc(Long userId, Pageable pageable);
}
