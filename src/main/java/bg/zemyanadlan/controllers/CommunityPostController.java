package bg.zemyanadlan.controllers;

import bg.zemyanadlan.dtos.*;
import bg.zemyanadlan.entities.Comment;
import bg.zemyanadlan.entities.CommunityPost;
import bg.zemyanadlan.entities.PostType;
import bg.zemyanadlan.entities.User;
import bg.zemyanadlan.mappers.CommentMapper;
import bg.zemyanadlan.mappers.CommunityPostMapper;
import bg.zemyanadlan.repositories.UserRepository;
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

/**
 * Controller for managing community posts, comments, and discussions.
 */
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
    private final UserRepository userRepository;


    /**
     * Retrieves the community feed with optional filtering by post type.
     * @param postType optional filter by post type (RECIPE, TIP, STORY)
     * @param pageable pagination information
     * @return a page of community post feed DTOs
     */
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


    /**
     * Retrieves the details of a community post along with its comments, incrementing the view count.
     * @param id the post ID
     * @return the community post details DTO
     */
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


    /**
     * Creates a new community post.
     * @param request the request DTO containing post details
     * @return the created community post feed DTO
     */
    @Operation(
            summary = "Create a new community post",
            description = "Creates a new community post of the specified type (RECIPE, TIP, STORY) with the provided content.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommunityPostFeedDto createPost(
            @Valid @RequestBody CreateCommunityPostRequestDto request
    ){
        User user = userRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("No users found in the database"));
        CommunityPost post = communityPostService.createPost(request, user);
        return communityPostMapper.toFeedDto(post);
    }

    @Operation(
            summary = "Update a community post",
            description = "Updates the content of an existing community post. " +
                    "Only the author of the post can perform this action.")
    @PutMapping("/{id}")
    public CommunityPostFeedDto updatePost(
            @Parameter(description = "Post ID")
            @PathVariable Long id,
            @Valid @RequestBody UpdateCommunityPostRequestDto request
    ){
        User user = userRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("No users found in the database"));;
        CommunityPost updatedPost = communityPostService.updatePost(id, request, user);
        return communityPostMapper.toFeedDto(updatedPost);
    }


    @Operation(
            summary = "Delete a community post",
            description = "Deletes an existing community post. Only the author of the post can perform this action.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deletePost(
            @Parameter(description = "Post ID")
            @PathVariable Long id
    ){
        User user = userRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("No users found in the database"));;
        communityPostService.deletePost(id, user);
    }


    /**
     * Adds a comment to a community post.
     * @param id the post ID
     * @param request the request DTO containing comment content
     * @return the created comment DTO
     */
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
        User user = userRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("No users found in the database"));;
        Comment comment = communityPostService.addComment(
                id,
                request.getContent(),
                user
        );
        return commentMapper.toDto(comment);
    }

    @Operation(
            summary = "Delete a comment",
            description = "Deletes an existing comment. " +
                    "Only the author of the comment can perform this action.")
    @DeleteMapping("/comments/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(
            @Parameter(description = "Comment ID")
            @PathVariable Long Id
    ){
        User user = userRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("No users found in the database"));
        communityPostService.deleteComment(Id, user);
    }

    @Operation(
            summary = "Update a comment",
            description = "Updates the content of an existing comment. " +
                    "Only the author of the comment can perform this action.")
    @PutMapping("/comments/{id}")
    public CommentDto updateComment(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCommentRequestDto request
    ) {
        User user = userRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("No users found in the database"));
        Comment comment = communityPostService.updateComment(id, request.getContent(), user);
        return commentMapper.toDto(comment);
    }
}
