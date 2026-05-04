package bg.zemyanadlan.mappers;

import bg.zemyanadlan.dtos.CreateMarketListingRequestDto;
import bg.zemyanadlan.dtos.MarketListingResponseDto;
import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.entities.MarketListing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MarketListingMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "categories", ignore = true)
    MarketListing toEntity(CreateMarketListingRequestDto dto);


    @Mapping(target = "authorName", source = "user.username")
    @Mapping(target = "categories", expression = "java(mapCategories(marketListing.getCategories()))")
    MarketListingResponseDto toResponseDto(MarketListing marketListing);


    default Set<String> mapCategories(Set<Category> categories) {
        if (categories == null) {
            return Set.of();
        }
        return categories.stream()
                .map(Category::getSlug)
                .collect(Collectors.toSet());
    }
}
