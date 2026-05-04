package bg.zemyanadlan.dtos;

import bg.zemyanadlan.entities.EventType;
import bg.zemyanadlan.entities.ListingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EventListingResponseDto {
    private Long id;
    private String name;
    private String description;
    private EventType eventType;
    private String address;
    private String city;
    private LocalDateTime startsAt;
    private String thumbnailUrl;
    private ListingStatus status;
    private String authorName;
    private Set<String> categories;
}
