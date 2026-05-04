package bg.zemyanadlan.controllers;


import bg.zemyanadlan.dtos.CreateMarketListingRequestDto;
import bg.zemyanadlan.dtos.MarketListingResponseDto;
import bg.zemyanadlan.entities.MarketListing;
import bg.zemyanadlan.entities.User;
import bg.zemyanadlan.mappers.MarketListingMapper;
import bg.zemyanadlan.services.MarketListingService;
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


@Tag(name = "Market", description = "Endpoints for managing market listings")
@RestController
@RequestMapping("/market")
@AllArgsConstructor
public class MarketListingController {

    private final MarketListingService marketListingService;
    private final MarketListingMapper marketListingMapper;
    private final UserService userService;


    @Operation(summary = "Create a new market product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MarketListingResponseDto createMarketListing(
            @Valid @RequestBody CreateMarketListingRequestDto request
    ){
        User currentUser = userService.getCurrentUser();
        MarketListing listing = marketListingMapper.toEntity(request);
        MarketListing savedListing = marketListingService.createMarketListing(
                listing,
                currentUser,
                request.getCategorySlug()
        );
        return marketListingMapper.toResponseDto(savedListing);
    }


    @Operation(summary = "Get a market product by ID")
    @GetMapping("/{id}")
    public MarketListingResponseDto getMarketListingById(@PathVariable Long id) {
        MarketListing listing = marketListingService.getMarketListingById(id);
        return marketListingMapper.toResponseDto(listing);
    }


    @Operation(summary = "List market products by category")
    @GetMapping
    public Page<MarketListingResponseDto> list(
            @RequestParam(required = false) String category,
            @ParameterObject Pageable pageable
    ){
        return marketListingService.listAll(category, pageable)
                .map(marketListingMapper::toResponseDto);
    }
}
