package bg.zemyanadlan.domain.listing;

import bg.zemyanadlan.domain.event.Event;
import bg.zemyanadlan.domain.location.Location;
import bg.zemyanadlan.user.User;

public class EventListing extends Listing{

    private final Event event;
    public EventListing(String title, User owner, Location location, Event event) {
        super(title, owner, location, null);
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
