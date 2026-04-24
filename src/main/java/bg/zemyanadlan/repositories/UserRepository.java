package bg.zemyanadlan.repositories;

import bg.zemyanadlan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
