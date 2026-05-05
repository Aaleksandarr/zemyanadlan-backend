package bg.zemyanadlan.dtos;

import bg.zemyanadlan.entities.ListingStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CraftListingResponseDto {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String material;
    private boolean isCustomizable;
    private String thumbnailUrl;
    private ListingStatus status;
    private String authorName;
    private Set<String> categories;
}
