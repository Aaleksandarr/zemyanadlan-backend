package bg.zemyanadlan.controllers;


import bg.zemyanadlan.dtos.CreateEventListingRequestDto;
import bg.zemyanadlan.dtos.EventListingResponseDto;
import bg.zemyanadlan.entities.EventListing;
import bg.zemyanadlan.entities.User;
import bg.zemyanadlan.mappers.EventListingMapper;
import bg.zemyanadlan.services.EventListingService;
import bg.zemyanadlan.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Events", description = "Endpoints for managing event listings")
@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventListingController {
    private final EventListingService eventListingService;
    private final EventListingMapper eventListingMapper;
    private final UserService userService;

    @Operation(summary = "Create a new event")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventListingResponseDto create(
            @Valid @RequestBody CreateEventListingRequestDto request
    ) {
        User currentUser = userService.getCurrentUser();
        EventListing listing = eventListingMapper.toEntity(request);
        EventListing saveListing = eventListingService.createEventListing(
                listing,
                request.getCategorySlug(),
                currentUser
        );
        return eventListingMapper.toDto(saveListing);
    }

    @Operation(summary = "Get event by ID")
    @GetMapping("/{id}")
    public EventListingResponseDto getById(@PathVariable Long id) {
        EventListing listing = eventListingService.getEventListingById(id);
        return eventListingMapper.toDto(listing);
    }

    @Operation(summary = "List events with optional category filter")
    @GetMapping
    public Page<EventListingResponseDto> list(
            @RequestParam(required = false) String category,
            @ParameterObject Pageable pageable
    ){
        return eventListingService.list(category, pageable)
                .map(eventListingMapper::toDto);
    }
}
