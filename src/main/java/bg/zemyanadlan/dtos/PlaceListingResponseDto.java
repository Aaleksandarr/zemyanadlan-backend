package bg.zemyanadlan.dtos;

import bg.zemyanadlan.entities.ListingStatus;
import bg.zemyanadlan.entities.PlaceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class PlaceListingResponseDto {
    private Long id;
    private String name;
    private String description;
    private PlaceType placeType;
    private String address;
    private String city;
    private String phone;
    private String website;
    private String thumbnailUrl;
    private ListingStatus status;
    private LocalDateTime createdAt;
    private String authorName;
    private Set<String> categories;
}
