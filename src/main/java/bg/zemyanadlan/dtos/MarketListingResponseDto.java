package bg.zemyanadlan.dtos;

import bg.zemyanadlan.entities.ListingStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class MarketListingResponseDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String unit;
    private String thumbnailUrl;
    private ListingStatus status;
    private LocalDateTime createdAt;
    private String authorName;
    private Set<String> categories;

}
