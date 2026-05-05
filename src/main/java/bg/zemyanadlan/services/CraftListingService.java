package bg.zemyanadlan.services;

import bg.zemyanadlan.entities.CraftListing;
import bg.zemyanadlan.entities.ListingStatus;
import bg.zemyanadlan.entities.User;
import bg.zemyanadlan.exceptions.ResourceNotFoundException;
import bg.zemyanadlan.repositories.CraftListingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
public class CraftListingService {
    private final CategoryService categoryService;
    private final CraftListingRepository craftListingRepository;

    @Transactional
    public CraftListing createCraftListing(
            CraftListing craftListing,
            User currentUser,
            Set<String> categorySlugs
    ) {
        craftListing.setUser(currentUser);
        craftListing.setStatus(ListingStatus.DRAFT);
        craftListing.setCreatedAt(LocalDateTime.now());
        craftListing.setCategories(categoryService.getCategoriesBySlugs(categorySlugs));

        return craftListingRepository.save(craftListing);
    }

    @Transactional(readOnly = true)
    public CraftListing getCraftListingById(Long Id){
        return craftListingRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Craft listing not found with id: " + Id));
    }

    @Transactional(readOnly = true)
    public Page<CraftListing> list(String categoryBySlug, Pageable pageable) {
        if (categoryBySlug == null || categoryBySlug.isBlank()) {
            return craftListingRepository.findAllByOrderByCreatedAtDesc(pageable);
        } else {
            return craftListingRepository.findAllByCategories_SlugOrderByCreatedAtDesc(categoryBySlug, pageable);
        }
    }

}
