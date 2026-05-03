package bg.zemyanadlan.controllers;

import bg.zemyanadlan.dtos.*;
import bg.zemyanadlan.entities.Comment;
import bg.zemyanadlan.entities.CommunityPost;
import bg.zemyanadlan.entities.PostType;
import bg.zemyanadlan.entities.User;
import bg.zemyanadlan.mappers.CommentMapper;
import bg.zemyanadlan.mappers.CommunityPostMapper;
import bg.zemyanadlan.services.CommunityPostService;
import bg.zemyanadlan.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Community",
        description = "Community posts, comments and discussions.")
@RestController
@AllArgsConstructor
@RequestMapping("/community/posts")
public class CommunityPostController {
    private final CommunityPostService communityPostService;
    private final CommunityPostMapper communityPostMapper;
    private final CommentMapper commentMapper;
    private final UserService userService;


    @Operation(summary = "Get community feed with optional filtering by post type(RECIPE, TIP, STORY)")
    @GetMapping
    public Page<CommunityPostFeedDto> getCommunityFeed(
            @Parameter(description = "Optional filter by post type")
            @RequestParam(required = false) PostType postType,

            @Parameter(hidden = true)
            @ParameterObject Pageable pageable
    ){
        return communityPostService.getCommunityFeed(postType, pageable)
                .map(communityPostMapper::toFeedDto);
    }


    @Operation(
            summary = "Get community post details",
            description = "Returns the details of a community post along with its comments. Increments the view count of the post.")
    @GetMapping("/{id}")
    public CommunityPostDetailsDto getPost(@Parameter(description = "Post ID") @PathVariable Long id){
        CommunityPost post = communityPostService.getPostAndIncrementViews(id);
        CommunityPostDetailsDto detailsDto = communityPostMapper.toDetailsDto(post);
        List<CommentDto> comments = communityPostService.getComments(post).stream()
                .map(commentMapper::toDto)
                .toList();

        detailsDto.setComments(comments);
        return detailsDto;
    }


    @Operation(
            summary = "Create a new community post",
            description = "Creates a new community post of the specified type (RECIPE, TIP, STORY) with the provided content.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommunityPostFeedDto createPost(
            @Valid @RequestBody CreateCommunityPostRequestDto request
    ){
        User user = userService.getCurrentUser();
        CommunityPost post = communityPostService.createPost(request, user);
        return communityPostMapper.toFeedDto(post);
    }

    @Operation(
            summary = "Add a comment to a community post",
            description = "Adds a new comment to the specified community post.")
    @PostMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addComment(
            @Parameter(description = "Post ID")
            @PathVariable Long id,
            @Valid @RequestBody CreateCommentRequestDto request
    ){
        User user = userService.getCurrentUser();
        Comment comment = communityPostService.addComment(
                id,
                request.getContent(),
                user
        );
        return commentMapper.toDto(comment);
    }

}
