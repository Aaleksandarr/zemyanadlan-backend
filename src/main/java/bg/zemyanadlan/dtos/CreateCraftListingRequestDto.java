package bg.zemyanadlan.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CreateCraftListingRequestDto {
    @NotBlank
    private String title;

    private String description;

    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    private String material;

    private boolean isCustomizable;

    private String thumbnailUrl;

    private Set<String> categorySlug;
}
