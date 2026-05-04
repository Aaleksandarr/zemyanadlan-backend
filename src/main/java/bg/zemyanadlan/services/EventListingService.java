package bg.zemyanadlan.services;

import bg.zemyanadlan.entities.EventListing;
import bg.zemyanadlan.entities.ListingStatus;
import bg.zemyanadlan.entities.User;
import bg.zemyanadlan.exceptions.ResourceNotFoundException;
import bg.zemyanadlan.repositories.EventListingRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.ReactiveTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
public class EventListingService {
    private final EventListingRepository eventListingRepository;
    private final CategoryService categoryService;


    @Transactional
    public EventListing createEventListing(
            EventListing eventListing,
            Set<String> categorySlugs,
            User currentUser) {
        eventListing.setUser(currentUser);
        eventListing.setStatus(ListingStatus.DRAFT);
        eventListing.setCreatedAt(LocalDateTime.now());
        eventListing.setCategories(categoryService.getCategoriesBySlugs(categorySlugs));
        return eventListingRepository.save(eventListing);
    }

    @Transactional(readOnly = true)
    public EventListing getEventListingById(Long id) {
        return eventListingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event listing not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Page<EventListing> list(
            String categorySlug,
            Pageable pageable
    ){
        if (categorySlug == null || categorySlug.isBlank()) {
            return eventListingRepository.findAllByOrderByCreatedAtDesc(pageable);
        } else {
            return eventListingRepository.findAllByCategories_slugOrderByCreatedAtDesc(categorySlug, pageable);
        }
    }

}
