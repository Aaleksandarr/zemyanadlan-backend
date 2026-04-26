//package bg.zemyanadlan.service;
//
//import bg.zemyanadlan.domain.event.EventType;
//import bg.zemyanadlan.domain.listing.EventListing;
//import bg.zemyanadlan.domain.listing.Listing;
//import bg.zemyanadlan.domain.listing.ListingStatus;
//import bg.zemyanadlan.domain.listing.ProductListing;
//
//import java.time.LocalDate;
//import java.util.Comparator;
//import java.util.List;
//
//public class ListingService {
//
//    //status filter
//    public <T extends Listing> List<T> getActive(List<T> listings){
//        return listings.stream()
//                .filter(listing -> listing.getStatus() == ListingStatus.ACTIVE)
//                .toList();
//    }
//
//    //city filter
//    public <T extends Listing > List<T> filterByCity(List<T> listings, String city){
//        if(city==null||city.isBlank()) {return listings;}
//
//        return listings.stream()
//                .filter(listing -> listing.getLocation() != null)
//                .filter(listing -> city.equals(listing.getLocation().getCity()))
//                .toList();
//    }
//
//    //text search
//    public <T extends Listing> List<T> searchByTitleContains(List<T> listings, String query){
//        if (query == null || query.isBlank()) {
//            return listings;
//        }
//        return listings.stream()
//                .filter(listing -> listing.getTitle().toLowerCase().contains(query.toLowerCase()))
//                .toList();
//    }
//
//    //pagination
//    public <T extends  Listing> List<T> paginate(List<T> listings, int page, int size){
//        if (page == 0 || size <=0){
//            return listings;
//        }
//        long skip = (long)page * size;
//        return listings.stream()
//                .skip(skip)
//                .limit(size)
//                .toList();
//
//    }
//
//    //sorting by date
//    public <T extends Listing> List<T> sortByCreatedAtDesc(List<T> listings){
//        return listings.stream()
//                .sorted(Comparator.comparing(Listing::getCreatedAt).reversed())
//                .toList()
//                .reversed();
//    }
//
//    //sorting by price
//    public <T extends Listing> List<T> sortByPriceAsc(List<T> listings){
//        return listings.stream()
//                .filter(Listing::hasPrice)
//                .sorted(Comparator.comparing(Listing::getPrice))
//                .toList();
//    }
//
//    //sorting by price desc
//    public <T extends Listing >List<T> sortByPriceDesc(List<T> listings){
//        return listings.stream()
//                .filter(Listing::hasPrice)
//                .sorted(Comparator.comparing(Listing::getPrice).reversed())
//                .toList();
//    }
//
//    //type filter
//    public <T extends Listing> List<T> filterByType(List<Listing> listings, Class<T> type){
//        return listings.stream()
//                .filter(type::isInstance)
//                .map(type::cast)
//                .toList();
//    }
//
//    //upcoming events
//    public List<EventListing> getUpcomingEvents(List<Listing> listings){
//        List<EventListing> events = filterByType(listings, EventListing.class);
//        LocalDate today = LocalDate.now();
//        return events.stream()
//                .filter(eventListing -> !eventListing.getEvent().getDate().isBefore(today))
//                .toList();
//    }
//
//    //past events
//    public List<EventListing> getPastEvents(List<Listing> listings){
//        List<EventListing> events = filterByType(listings, EventListing.class);
//        LocalDate today = LocalDate.now();
//        return events.stream()
//                .filter(eventListing -> eventListing.getEvent().getDate().isBefore(today))
//                .toList();
//    }
//
//    //event type filter
//    public List<EventListing> filterEventsByEventType(
//            List<EventListing> events,
//            EventType type
//    ) {
//        if (type == null){
//            return events;
//        }
//        return events.stream()
//                .filter(eventListing -> eventListing.getEvent().getType() != null &&
//                        eventListing.getEvent().getType() == type)
//                .toList();
//    }
//
//    // -----
//    // PIPELINES
//
//    public List<Listing> findListing(
//            List<Listing> listings,
//            String city,
//            String search,
//            int page,
//            int size
//    ) {
//        List<Listing> result = listings;
//        result = getActive(result);
//        result = filterByCity(result, city);
//        result = searchByTitleContains(result, search);
//        result = sortByCreatedAtDesc(result);
//        result = paginate(result, page, size);
//
//        return result;
//    }
//
//    public List<EventListing> findUpcomingEvents(
//            List<Listing> listings,
//            String city,
//            int page,
//            int size
//    ) {
//        List<EventListing> result;
//        result = getUpcomingEvents(listings);
//        result = filterByCity(result, city);
//        result = sortByCreatedAtDesc(result);
//        result = paginate(result, page, size);
//
//        return result;
//    }
//
//    public List<EventListing> findUpcomingEvents(
//            List<Listing> listings,
//            String city,
//            EventType eventType,
//            int page,
//            int size
//    ) {
//        List<EventListing> result;
//        result = getUpcomingEvents(listings);
//        result = filterEventsByEventType(result, eventType);
//        result = filterByCity(result, city);
//        result = sortByCreatedAtDesc(result);
//        result = paginate(result, page, size);
//
//        return result;
//    }
//
//    public List<EventListing> findPastEvents(
//            List<Listing> listings,
//            String city,
//            int page,
//            int size
//    ) {
//        List<EventListing> result;
//        result = getPastEvents(listings);
//        result = filterByCity(result, city);
//        result = sortByCreatedAtDesc(result);
//        result = paginate(result, page, size);
//
//        return result;
//    }
//
//    public List<ProductListing> findProducts(
//            List<Listing> listings,
//            String city,
//            String search,
//            boolean sortAsc,
//            int page,
//            int size
//    ) {
//        List<ProductListing> result;
//        result = filterByType(listings, ProductListing.class);
//        result = getActive(result);
//        result = filterByCity(result, city);
//        result = searchByTitleContains(result, search);
//        if (sortAsc)
//            result = sortByPriceAsc(result);
//        else
//            result = sortByPriceDesc(result);
//        result = paginate(result, page, size);
//
//        return result;
//    }
//
//
//
//
//
//
//
//
//
//    //  TO DO: category/subtype filters.
//}
