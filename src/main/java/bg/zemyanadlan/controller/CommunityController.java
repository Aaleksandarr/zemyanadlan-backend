//package bg.zemyanadlan.controller;
//
//import bg.zemyanadlan.domain.community.PostCategory;
//import bg.zemyanadlan.dto.CommunityPostResponse;
//import bg.zemyanadlan.service.CommunityService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
//@RestController
//public class CommunityController {
//    private final CommunityService communityService;
//
//    public CommunityController(CommunityService communityService) {
//        this.communityService = communityService;
//    }
//
//    @GetMapping("/community")
//    public List<CommunityPostResponse> getCommunityPosts(
//            @RequestParam(required = false) String search,
//            @RequestParam(required = false) PostCategory category
//    ) {
//        return communityService.getMockPosts(search, category);
//    }
//
//}
