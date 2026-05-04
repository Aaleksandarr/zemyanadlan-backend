package bg.zemyanadlan.controllers;


import bg.zemyanadlan.dtos.CraftListingResponseDto;
import bg.zemyanadlan.dtos.CreateCraftListingRequestDto;
import bg.zemyanadlan.entities.CraftListing;
import bg.zemyanadlan.entities.User;
import bg.zemyanadlan.mappers.CraftListingMapper;
import bg.zemyanadlan.services.CraftListingService;
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



@Tag(name = "Crafts", description = "Endpoints for managing craft listings")
@RestController
@RequestMapping("/crafts")
@AllArgsConstructor
public class CraftListingController {
    private final CraftListingService craftListingService;
    private final UserService userService;
    private final CraftListingMapper craftListingMapper;

    @Operation(summary = "Create a new craft")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CraftListingResponseDto createCraftListing(
            @Valid @RequestBody CreateCraftListingRequestDto requestDto
            ) {
        User currentUser = userService.getCurrentUser();
        CraftListing craftListing = craftListingMapper.toEntity(requestDto);
        CraftListing savedListing = craftListingService.createCraftListing(
                craftListing,
                currentUser,
                requestDto.getCategorySlug()
        );
        return craftListingMapper.toResponseDto(savedListing);
    }

    @Operation(summary = "Get a craft by ID")
    @GetMapping("/{id}")
    public CraftListingResponseDto getCraftListingById(@PathVariable Long id) {
        CraftListing craftListing = craftListingService.getCraftListingById(id);
        return craftListingMapper.toResponseDto(craftListing);
    }

    @Operation(summary = "List crafts with optional category filter")
    @GetMapping
    public Page<CraftListingResponseDto> list(
            @RequestParam(required = false) String category,
            @ParameterObject Pageable pageable
    ) {
        return craftListingService.list(category, pageable)
                .map(craftListingMapper::toResponseDto);
    }

}

