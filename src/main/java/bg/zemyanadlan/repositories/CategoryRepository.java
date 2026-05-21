package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.entities.CategoryScope;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findBySlug(String slug);
    List<Category> findAllByScope(CategoryScope categoryScope);
}
