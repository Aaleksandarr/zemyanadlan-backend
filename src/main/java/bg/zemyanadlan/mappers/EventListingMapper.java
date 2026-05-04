package bg.zemyanadlan.mappers;


import bg.zemyanadlan.dtos.EventListingResponseDto;
import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.entities.EventListing;
import bg.zemyanadlan.dtos.CreateEventListingRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EventListingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "categories", ignore = true)
    EventListing toEntity(CreateEventListingRequestDto request);

    @Mapping(target = "authorName", source = "user.username")
    @Mapping(target = "categories", expression = "java(mapCategories(listing.getCategories()))")
    EventListingResponseDto toDto(EventListing listing);


    default Set<String> mapCategories(Set<Category> categories) {
        if (categories == null) {
            return Set.of();
        }
        return categories.stream()
                .map(Category::getSlug)
                .collect(Collectors.toSet());
    }


}
