package bg.zemyanadlan.controllers;

import bg.zemyanadlan.dtos.CategoryResponseDto;
import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.entities.CategoryScope;
import bg.zemyanadlan.mappers.CategoryMapper;
import bg.zemyanadlan.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    @GetMapping
    public List<CategoryResponseDto> getCategoriesByScope(@RequestParam CategoryScope scope) {
        return categoryService.getCategoriesByScope(scope)
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @GetMapping("/{slug}")
    public CategoryResponseDto getBySlug(@PathVariable String slug) {
        Category category = categoryService.getCategoryBySlug(slug);
        return categoryMapper.toDto(category);
    }

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
