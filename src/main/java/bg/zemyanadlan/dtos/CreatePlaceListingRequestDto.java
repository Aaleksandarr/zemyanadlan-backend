package bg.zemyanadlan.dtos;

import bg.zemyanadlan.entities.PlaceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class CreatePlaceListingRequestDto {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private PlaceType placeType;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    private String phone;

    private String website;

    private String thumbnailUrl;

    @NotNull
    private Set<String> categorySlug;
}
