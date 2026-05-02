package bg.zemyanadlan.mappers;

import bg.zemyanadlan.dtos.CategoryResponseDto;
import bg.zemyanadlan.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponseDto toDto(Category category);
    Category toEntity(CategoryResponseDto categoryResponseDto);
}
