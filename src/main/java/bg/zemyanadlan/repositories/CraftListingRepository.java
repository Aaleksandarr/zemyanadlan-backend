package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.CraftListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CraftListingRepository extends JpaRepository<CraftListing, Long> {
}
