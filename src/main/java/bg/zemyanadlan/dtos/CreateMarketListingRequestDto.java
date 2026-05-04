package bg.zemyanadlan.dtos;

import bg.zemyanadlan.entities.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CreateMarketListingRequestDto {
    @NotBlank
    private String title;

    private String description;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    private String unit;

    private String thumbnailUrl;

    @NotNull
    private Set<String> categorySlug;
}
