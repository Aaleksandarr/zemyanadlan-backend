package bg.zemyanadlan.services;

import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.entities.CategoryScope;

import java.util.List;

public interface CategoryService {
     Category createCategory(String name, String slug, CategoryScope scope);

     List<Category> getCategoriesByScope(CategoryScope scope);

     Category getCategoryBySlug(String slug);
}
