package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
