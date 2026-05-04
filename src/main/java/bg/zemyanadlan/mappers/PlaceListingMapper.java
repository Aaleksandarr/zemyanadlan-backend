package bg.zemyanadlan.mappers;

import bg.zemyanadlan.dtos.CreatePlaceListingRequestDto;
import bg.zemyanadlan.dtos.PlaceListingResponseDto;
import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.entities.PlaceListing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PlaceListingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "categories", ignore = true)
    PlaceListing toEntity(CreatePlaceListingRequestDto dto);

    @Mapping(target = "authorName", source = "user.username")
    @Mapping(target = "categories", expression = "java(mapCategories(listing.getCategories()))")
    PlaceListingResponseDto toResponseDto(PlaceListing listing);

    default Set<String> mapCategories(Set<Category> categories) {
        if (categories == null) {
            return Set.of();
        }
        return categories.stream()
                .map(Category::getSlug)
                .collect(Collectors.toSet());
    }
}


