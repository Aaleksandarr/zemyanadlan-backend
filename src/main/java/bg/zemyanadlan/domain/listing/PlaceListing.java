package bg.zemyanadlan.domain.listing;

import bg.zemyanadlan.domain.location.Location;
import bg.zemyanadlan.domain.place.Place;
import bg.zemyanadlan.user.User;


public class PlaceListing extends Listing{
    private Place place;

    public PlaceListing(String title, User owner, Location location, Place place) {
        super(title, owner, location, null);
        this.place = place;
    }
}
