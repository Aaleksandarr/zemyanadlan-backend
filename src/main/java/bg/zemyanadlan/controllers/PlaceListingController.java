package bg.zemyanadlan.controllers;


import bg.zemyanadlan.dtos.CreatePlaceListingRequestDto;
import bg.zemyanadlan.dtos.PlaceListingResponseDto;
import bg.zemyanadlan.entities.PlaceListing;
import bg.zemyanadlan.entities.User;
import bg.zemyanadlan.mappers.PlaceListingMapper;
import bg.zemyanadlan.repositories.UserRepository;
import bg.zemyanadlan.services.PlaceListingService;
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

@Tag(name = "Places", description = "Endpoints for managing place listings")
@RestController
@AllArgsConstructor
@RequestMapping("/places")
public class PlaceListingController {
    private final PlaceListingService placeListingService;
    private final PlaceListingMapper placeListingMapper;
    private final UserService userService;

    @Operation(summary = "Create a new place")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceListingResponseDto createPlaceListing(
            @Valid @RequestBody CreatePlaceListingRequestDto request
            ) {
        User currentUser = userService.getCurrentUser();
        PlaceListing listing = placeListingMapper.toEntity(request);
        PlaceListing savedPlace = placeListingService.createPlaceListing(
                listing,
                currentUser,
                request.getCategorySlug()
        );
        return placeListingMapper.toResponseDto(savedPlace);
    }

    @Operation(summary = "Get a place by ID")
    @GetMapping("/{id}")
    public PlaceListingResponseDto getPlaceListingById(@PathVariable Long id) {
        PlaceListing listing = placeListingService.getPlaceListingById(id);
        return placeListingMapper.toResponseDto(listing);
    }


    @Operation(summary = "List places by category")
    @GetMapping
    public Page<PlaceListingResponseDto> list(
            @RequestParam(required = false) String category,
            @ParameterObject Pageable pageable
    ){
        return placeListingService.list(category, pageable)
                .map(placeListingMapper::toResponseDto);
    }

}
