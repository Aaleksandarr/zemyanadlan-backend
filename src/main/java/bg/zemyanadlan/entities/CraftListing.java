package bg.zemyanadlan.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "craft_listings")
public class CraftListing {
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

    @Column(name = "material")
    private String material;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",
            columnDefinition = "varchar(20)")
    private ListingStatus status;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_customizable")
    private boolean isCustomizable;

}
