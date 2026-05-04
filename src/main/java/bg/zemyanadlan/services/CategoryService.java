package bg.zemyanadlan.services;

import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.entities.CategoryScope;

import java.util.List;
import java.util.Set;

public interface CategoryService {
     Category createCategory(String name, String slug, CategoryScope scope);

     List<Category> getCategoriesByScope(CategoryScope scope);

     Category getCategoryBySlug(String slug);

     Set<Category> getCategoriesBySlugs(Set<String> slugs);
}
