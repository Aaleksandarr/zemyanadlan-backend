package bg.zemyanadlan.services;

import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.entities.ListingStatus;
import bg.zemyanadlan.entities.MarketListing;
import bg.zemyanadlan.entities.User;
import bg.zemyanadlan.repositories.CategoryRepository;
import bg.zemyanadlan.repositories.MarketListingRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@AllArgsConstructor
public class MarketListingService {
    private final MarketListingRepository marketListingRepository;
    private final CategoryRepository categoryRepository;
    private final ListableBeanFactory listableBeanFactory;
    private final CategoryService categoryService;

    @Transactional
    public MarketListing createMarketListing(
            MarketListing marketListing,
            User currentUser,
            Set<String> categorySlugs
    ) {
        marketListing.setUser(currentUser);
        marketListing.setStatus(ListingStatus.DRAFT);
        marketListing.setCategories(categoryService.getCategoriesBySlugs(categorySlugs));
        return marketListingRepository.save(marketListing);
    }

    @Transactional(readOnly = true)
    public MarketListing getMarketListingById(Long id) {
        return marketListingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Market listing not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Page<MarketListing> listByCategory(
            String categorySlug,
            Pageable pageable
    ){
        return marketListingRepository.findAllByCategories_SlugOrderByCreatedAtDesc(categorySlug, pageable);
    }



}
