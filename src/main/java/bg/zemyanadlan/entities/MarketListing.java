package bg.zemyanadlan.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a market listing in the marketplace, including details like price, unit, and associated categories.
 */
@Getter
@Setter
@Entity
@Table(name = "market_listings")
public class MarketListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "unit")
    private String unit;

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
     * The user who created the market listing.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The set of categories associated with this market listing.
     */
    @ManyToMany
    @JoinTable(
            name = "market_listing_categories",
            joinColumns = @JoinColumn(name = "listing_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
}
