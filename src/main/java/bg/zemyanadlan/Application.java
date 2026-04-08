package bg.zemyanadlan;

import bg.zemyanadlan.domain.event.Event;
import bg.zemyanadlan.domain.event.EventType;
import bg.zemyanadlan.domain.listing.*;
import bg.zemyanadlan.domain.location.Location;
import bg.zemyanadlan.domain.place.Place;
import bg.zemyanadlan.domain.place.PlaceType;
import bg.zemyanadlan.domain.product.Product;
import bg.zemyanadlan.domain.product.ProductCategory;
import bg.zemyanadlan.service.ListingService;
import bg.zemyanadlan.user.Role;
import bg.zemyanadlan.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        User ivan = new User("Ivan", "vankata@gmail.com", Role.PRODUCER);
        User rumen = new User("Rumen", "rumbata@abv.bg", Role.PRODUCER);
        Location sofia = new Location("Sofia");
        Location plovdiv = new Location("Plovdiv");


        ProductListing productListing =
                new ProductListing("Fresh tomatoes", ivan, sofia, BigDecimal.valueOf(3.50), new Product("Tomatoes", ProductCategory.VEGETABLES));

        HandmadeListing handmadeListing =
                new HandmadeListing("Wooden spoon", ivan, sofia, BigDecimal.valueOf(12.00));

        PlaceListing placeListing =
                new PlaceListing("Moma Food and Wine", rumen, sofia, new Place("Moma Food and Wine", PlaceType.RESTAURANT));

        EventListing eventListing =
                new EventListing("Village fair", ivan, plovdiv, new Event("Village fair", LocalDate.of(2026, 4, 7), EventType.FAIR));




        List<Listing> allListings = new ArrayList<>();

        allListings.add(productListing);
        allListings.add(handmadeListing);
        allListings.add(placeListing);
        allListings.add(eventListing);

        ListingService listingService = new ListingService();

        System.out.println("All listings: " + allListings.size());
        System.out.println("Active listings: " + listingService.getActive(allListings).size());
        System.out.println("Listing in Sofia: " + listingService.filterByCity(allListings, "sofia").size());
        List<ProductListing> products =
                listingService.filterByType(allListings, ProductListing.class);

        System.out.println("Product listing: " + products.size());


    }
}
