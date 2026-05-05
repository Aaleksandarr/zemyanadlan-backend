package bg.zemyanadlan.mappers;

import bg.zemyanadlan.dtos.CraftListingResponseDto;
import bg.zemyanadlan.dtos.CreateCraftListingRequestDto;
import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.entities.CraftListing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CraftListingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "categories", ignore = true)
    CraftListing toEntity(CreateCraftListingRequestDto dto);

    @Mapping(target = "authorName", source = "user.username")
    @Mapping(target = "categories", expression = "java(mapCategories(listing.getCategories()))")
    CraftListingResponseDto toResponseDto(CraftListing listing);

    default Set<String> mapCategories(Set<Category> categories) {
        if (categories == null) {
            return Set.of();
        }
        return categories.stream()
                .map(Category::getSlug)
                .collect(Collectors.toSet());
    }
}
