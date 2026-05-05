package bg.zemyanadlan.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a place listing, including details like place type, contact info, and associated categories.
 */
@Setter
@Getter
@Entity
@Table(name = "place_listings")
public class PlaceListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "place_type",
            columnDefinition = "varchar(50)")
    private PlaceType placeType;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",
            columnDefinition = "varchar(20)")
    private ListingStatus status;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * The user who created the place listing.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The set of categories associated with this place listing.
     */
    @ManyToMany
    @JoinTable(
            name = "place_listing_categories",
            joinColumns = @JoinColumn(name = "listing_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
}
