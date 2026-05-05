package bg.zemyanadlan.services;


import bg.zemyanadlan.entities.ListingStatus;
import bg.zemyanadlan.entities.PlaceListing;
import bg.zemyanadlan.entities.User;
import bg.zemyanadlan.exceptions.ResourceNotFoundException;
import bg.zemyanadlan.repositories.PlaceListingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
public class PlaceListingService {
    private final PlaceListingRepository placeListingRepository;
    private final CategoryService categoryService;

    @Transactional
    public PlaceListing createPlaceListing(
            PlaceListing placeListing,
            User currentUser,
            Set<String> categorySlug
    ) {
        placeListing.setUser(currentUser);
        placeListing.setStatus(ListingStatus.DRAFT);
        placeListing.setCreatedAt(LocalDateTime.now());
        placeListing.setCategories(categoryService.getCategoriesBySlugs(categorySlug));

        return placeListingRepository.save(placeListing);
    }

    @Transactional(readOnly = true)
    public  PlaceListing getPlaceListingById(Long id) {
        return placeListingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Place listing not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Page<PlaceListing> list(String categorySlug, Pageable pageable) {
        if (categorySlug == null || categorySlug.isBlank()) {
            return placeListingRepository.findAllByOrderByCreatedAtDesc(pageable);
        } else {
            return placeListingRepository.findAllByCategories_SlugOrderByCreatedAtDesc(categorySlug, pageable);
        }
    }
}
