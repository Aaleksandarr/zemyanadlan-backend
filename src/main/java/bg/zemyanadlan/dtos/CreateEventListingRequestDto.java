package bg.zemyanadlan.dtos;

import bg.zemyanadlan.entities.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class CreateEventListingRequestDto {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private EventType eventType;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotNull
    private LocalDateTime startsAt;

    private String thumbnailUrl;

    @NotNull
    private Set<String> categorySlug;
}
