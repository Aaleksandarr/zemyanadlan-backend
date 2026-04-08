package bg.zemyanadlan.domain.listing;

import bg.zemyanadlan.domain.location.Location;
import bg.zemyanadlan.user.User;

import java.math.BigDecimal;

public class HandmadeListing extends Listing{
    public HandmadeListing(String title,
                           User owner,
                           Location location,
                           BigDecimal price) {
        super(title, owner, location, price);
    }
}
