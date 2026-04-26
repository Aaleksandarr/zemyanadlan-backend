//package bg.zemyanadlan.service;
//
//
//import bg.zemyanadlan.domain.community.PostCategory;
//import bg.zemyanadlan.dto.CommunityPostResponse;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class CommunityService {
//    private final List<CommunityPostResponse> mockPosts = List.of(
//            new CommunityPostResponse(
//                    1L,
//                    "Как днешните продукти стигат до пазара",
//                    "История на пътя от градината до сергията.",
//                    "/images/farm.jpg",
//                    LocalDate.now().minusDays(2),
//                    "STORY"
//            ),
//            new CommunityPostResponse(
//                    2L,
//                    "Съвети за пролетно засяване",
//                    "Практични насоки от опитни градинари.",
//                    "/images/plants.jpg",
//                    LocalDate.now().minusDays(5),
//                    "ADVICE"
//            )
//    );
//
//    public List<CommunityPostResponse> getMockPosts(String search, PostCategory category){
//        return mockPosts.stream()
//                .filter(p ->
//                        search == null ||
//                        p.getTitle().toLowerCase().contains(search.toLowerCase()) ||
//                        p.getSummary().toLowerCase().contains(search.toLowerCase())
//                )
//                .filter(p -> category == null ||
//                        p.getCategory().equalsIgnoreCase(category.name())
//                )
//                .collect(Collectors.toList());
//    }
//
//}
//
