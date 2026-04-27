//package bg.zemyanadlan.service;
//
////import bg.zemyanadlan.domain.event.Event;
////import bg.zemyanadlan.domain.event.EventType;
////import bg.zemyanadlan.domain.listing.EventListing;
////import bg.zemyanadlan.domain.listing.Listing;
////
////import bg.zemyanadlan.domain.listing.ProductListing;
////import bg.zemyanadlan.domain.location.Location;
////import bg.zemyanadlan.domain.product.Product;
////import bg.zemyanadlan.domain.product.ProductCategory;
//import bg.zemyanadlan.user.Role;
//import bg.zemyanadlan.user.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class ListingServiceTest {
//    private ListingService listingService;
//
//    @BeforeEach
//    void setUp(){
//        listingService = new ListingService();
//    }
//
//
//    @Test
//    void findUpcomingEvents_returnsOnlyFutureAndTodayEvents(){
//        //Arrange
//        Event futureEvent = new Event(
//                "Festival",
//                LocalDate.now().plusDays(5),
//                EventType.FESTIVAL
//        );
//
//        Event pastEvent = new Event(
//                "Old Fair",
//                LocalDate.now().minusDays(3),
//                EventType.FAIR
//        );
//
//        EventListing futureListing = new EventListing(
//                "Festival listing",
//                new User("Sasho", "sasheto@gmail.com", Role.PRODUCER),
//                new Location("Bansko"),
//                futureEvent
//        );
//
//        EventListing pastListing = new EventListing(
//                "Old Fair listing",
//                new User("Maria", "sasheto@gmail.com", Role.PRODUCER),
//                new Location("Ruse"),
//                pastEvent
//        );
//        List<Listing> listing = List.of(futureListing, pastListing);
//
//
//        //Act
//        List<EventListing> result =
//                listingService.findUpcomingEvents(listing, null, 0, 10);
//
//
//        //Assert
//        assertEquals(1, result.size());
//        assertEquals(futureEvent, result.get(0).getEvent());
//        assertEquals("Festival", result.get(0).getEvent().getName());
//
//    }
//
//    @Test
//    void findUpcomingEvents_returnEmptyList_whenAllEventsAreInThePast() {
//        //Arrange
//        Event pastEvent1 = new Event(
//                "Old Fair",
//                LocalDate.now().minusDays(5),
//                EventType.FESTIVAL
//                );
//        Event pastEvent2 = new Event(
//                "Very Old Fest",
//                LocalDate.now().minusDays(30),
//                EventType.FESTIVAL
//        );
//
//        EventListing pastListing1 = new EventListing(
//                "Old fair listing",
//                new User("Pesho", "peshkata@gmail.com", Role.PRODUCER),
//                new Location("Montana"),
//                pastEvent1
//        );
//        EventListing pastListing2 = new EventListing(
//                "Old fair listing",
//                new User("Ana", "ancheto@gmail.com", Role.PRODUCER),
//                new Location("Varna"),
//                pastEvent2
//        );
//
//        List<Listing> listings = List.of(pastListing1, pastListing2);
//
//        //Act
//        List<EventListing> result = listingService.findUpcomingEvents(listings, null, 0, 10);
//
//        //Assert
//        assertEquals(0, result.size());
//    }
//    @Test
//    void findUpcomingEvents_filtersByEventType(){
//        //Arrange
//        Event festival = new Event(
//                "Festival",
//                LocalDate.now().plusDays(5),
//                EventType.FESTIVAL
//        );
//
//        Event fair = new Event(
//                "Fair",
//                LocalDate.now().plusDays(7),
//                EventType.FAIR
//        );
//
//        EventListing festivalListing = new EventListing(
//                "Festival listing",
//                new User("Kosara", "sara@gmail.com", Role.PRODUCER),
//                new Location("Gabrovo"),
//                festival
//        );
//        EventListing fairListing = new EventListing(
//                "Fair listing",
//                new User("Ivan", "vankata@gmail.com", Role.PRODUCER),
//                new Location("Stara Zagora"),
//                fair
//        );
//        List<Listing> listings = List.of(festivalListing, fairListing);
//
//        //Act
//        List<EventListing> result =
//                listingService.findUpcomingEvents(listings, null, EventType.FESTIVAL,0,10);
//
//        //Assert
//        assertEquals(1, result.size());
//        assertEquals("Festival", result.get(0).getEvent().getName());
//        assertEquals(EventType.FESTIVAL, result.get(0).getEvent().getType());
//    }
//
//    @Test
//    void findProducts_sortsByPriceAscending() {
//
//        //Arrange
//
//        Product cheapProduct = new Product("Cheap product", ProductCategory.VEGETABLES);
//        Product midProduct = new Product("Mid product", ProductCategory.DAIRY);
//        Product expensiveProduct = new Product("Expensive product", ProductCategory.MEAT);
//
//        ProductListing cheap = new ProductListing(
//                "Cheap product",
//                new User("Blagovesta", "betito@gmail.com", Role.PRODUCER),
//                new Location("Tarnovo"),
//                BigDecimal.valueOf(5),
//                cheapProduct
//                );
//        ProductListing mid = new ProductListing(
//                "Mid product",
//                new User("Irina", "ira@gmail.com", Role.PRODUCER),
//                new Location("Velingrad"),
//                BigDecimal.valueOf(10),
//                midProduct
//        );
//        ProductListing expensive = new ProductListing(
//                "Expensive product",
//                new User("Emil", "emo@gmail.com", Role.PRODUCER),
//                new Location("Omourtag"),
//                BigDecimal.valueOf(25),
//                expensiveProduct
//        );
//
//        List<Listing> listings = List.of(expensive, mid, cheap);
//
//        //Act
//        List<ProductListing> result =
//                listingService.findProducts(listings, null, null, true, 0, 10);
//
//        //Assert
//        assertEquals(3, result.size());
//        assertEquals(cheap, result.get(0));
//        assertEquals(mid, result.get(1));
//        assertEquals(expensive, result.get(2));
//    }
//
//    @Test
//    void findUpcomingEvents_doesNotFilterByCity_whenCityIsNull(){
//
//        //Arrange
//        Event futureEvent = new Event(
//                "Future event",
//                LocalDate.now().plusDays(5),
//                EventType.FESTIVAL
//        );
//        EventListing listing = new EventListing(
//                "Event listing",
//                new User("Teodor", "teo@commerzbank.com", Role.PRODUCER),
//                new Location("DopeRitch"),
//                futureEvent
//        );
//
//        List<Listing> listings = List.of(listing);
//
//        //Act
//        List<EventListing> result =
//                listingService.findUpcomingEvents(listings, null, 0,10);
//
//        //Assert
//        assertEquals(1, result.size());
//
//    }
//
//}
