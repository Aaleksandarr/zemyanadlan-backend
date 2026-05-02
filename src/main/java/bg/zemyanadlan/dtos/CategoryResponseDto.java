package bg.zemyanadlan.dtos;

import bg.zemyanadlan.entities.CategoryScope;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryResponseDto {
    private Long id;
    @NotBlank(message = "Category name must not be blank")
    private String name;
    @NotBlank(message = "Category slug must not be blank")
    private String slug;
    @NotNull(message = "Category scope must not be null")
    private CategoryScope scope;
}
