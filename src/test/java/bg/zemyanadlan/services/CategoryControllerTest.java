package bg.zemyanadlan.services;

import bg.zemyanadlan.entities.Category;
import bg.zemyanadlan.entities.CategoryScope;
import bg.zemyanadlan.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void getCategoriesByScope_ShouldReturnCategories() throws Exception {

        mockMvc.perform(get("/categories?scope=FOOD")
                        .param("scope", "MARKET"))
                        .andExpect(status().isOk())
                        .andExpect(content().json("[]"));
    }

}
