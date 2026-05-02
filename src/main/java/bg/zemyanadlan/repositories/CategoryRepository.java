package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.entities.CategoryScope;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findBySlug(String slug);
    List<Category> findAllByScope(CategoryScope categoryScope);
}
