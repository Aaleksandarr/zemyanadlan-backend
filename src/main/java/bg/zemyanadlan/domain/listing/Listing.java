package bg.zemyanadlan.domain.listing;

import bg.zemyanadlan.domain.location.Location;
import bg.zemyanadlan.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Listing {
    private final UUID id;
    private final String title;
    private final User owner;
    private final Location location;
    private final BigDecimal price;
    private final LocalDateTime createdAt;
    private ListingStatus status;


    protected Listing(String title, User owner, Location location, BigDecimal price) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.owner = owner;
        this.location = location;
        this.price = price;
        this.createdAt = LocalDateTime.now();
        this.status = ListingStatus.ACTIVE;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public User getOwner() {
        return owner;
    }

    public Location getLocation() {
        return location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ListingStatus getStatus() {
        return status;
    }

    public boolean hasPrice(){
        return price != null;
    }

    public void deactivate(){
        this.status = ListingStatus.INACTIVE;
    }
}
