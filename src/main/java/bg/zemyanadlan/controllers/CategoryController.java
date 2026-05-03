package bg.zemyanadlan.controllers;

import bg.zemyanadlan.dtos.CategoryResponseDto;
import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.entities.CategoryScope;
import bg.zemyanadlan.mappers.CategoryMapper;
import bg.zemyanadlan.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Categories", description = "Endpoints for managing categories(market, crafts, events, places).")
@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Operation(
            summary = "Get categories by scope",
            description = "Returns a list of categories filtered by the specified scope(MARKET, CRAFT, EVENT, PLACE).")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CategoryResponseDto> getCategoriesByScope(
            @Parameter(description = "Category scope", example = "MARKET")
            @RequestParam CategoryScope scope) {
        return categoryService.getCategoriesByScope(scope)
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }


    @Operation(
            summary = "Get category by slug",
            description = "Returns a category identified by its unique slug.")
    @GetMapping("/{slug}")
    public CategoryResponseDto getBySlug(
            @Parameter(description = "Unique slug of the category", example = "vegetables")
            @PathVariable String slug) {
        Category category = categoryService.getCategoryBySlug(slug);
        return categoryMapper.toDto(category);
    }


    @Operation(
            summary = "Create a new category",
            description = "Creates a new category with the provided name, slug, and scope.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDto create(@Valid @RequestBody CategoryResponseDto dto
    ) {
        Category category = categoryService.createCategory(
                dto.getName(),
                dto.getSlug(),
                dto.getScope()
        );
        return categoryMapper.toDto(category);
    }
}
