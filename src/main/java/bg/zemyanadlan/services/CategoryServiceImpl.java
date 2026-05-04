package bg.zemyanadlan.services;

import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.entities.CategoryScope;
import bg.zemyanadlan.exceptions.ResourceNotFoundException;
import bg.zemyanadlan.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public Category createCategory(String name, String slug, CategoryScope scope) {
        if (categoryRepository.findBySlug(slug).isPresent()){
            throw new IllegalArgumentException("Category with the slug '" + slug + "' already exists");
        }
        Category category = new Category();
        category.setName(name);
        category.setSlug(slug);
        category.setScope(scope);
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getCategoriesByScope(CategoryScope scope) {
        return categoryRepository.findAllByScope(scope);
    }

    @Transactional(readOnly = true)
    @Override
    public Category getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Category with the slug '" + slug + "' not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Category> getCategoriesBySlugs(Set<String> slugs) {
        if (slugs == null || slugs.isEmpty()) {
            return Set.of();
        }
        return slugs.stream()
                .map(this::getCategoryBySlug)
                .collect(Collectors.toSet());
    }
}
