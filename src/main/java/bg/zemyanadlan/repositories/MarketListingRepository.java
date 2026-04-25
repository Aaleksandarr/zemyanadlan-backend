package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.MarketListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketListingRepository extends JpaRepository<MarketListing, Long> {
}
