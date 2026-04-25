package bg.zemyanadlan;

import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class ZemyanadlanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZemyanadlanApplication.class, args);

    }
}
