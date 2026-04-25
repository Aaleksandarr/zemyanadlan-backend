package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.PlaceListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceListingRepository extends JpaRepository<PlaceListing, Long> {
}
