package bg.zemyanadlan.services;

import bg.zemyanadlan.dtos.CreateCommunityPostRequestDto;
import bg.zemyanadlan.dtos.UpdateCommentRequestDto;
import bg.zemyanadlan.dtos.UpdateCommunityPostRequestDto;
import bg.zemyanadlan.entities.*;
import bg.zemyanadlan.exceptions.ForbiddenException;
import bg.zemyanadlan.exceptions.ResourceNotFoundException;
import bg.zemyanadlan.mappers.CommunityPostMapper;
import bg.zemyanadlan.repositories.CommentRepository;
import bg.zemyanadlan.repositories.CommunityPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Service class for managing community posts.
 */
@Service
@AllArgsConstructor
public class CommunityPostService {
    private final CommunityPostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommunityPostMapper communityPostMapper;
    private final CategoryService categoryService;


    /**
     * Retrieves the community feed, optionally filtered by post type.
     *
     * @param postType the type of posts to retrieve (optional)
     * @param pageable pagination information
     * @return a page of community posts
     */
    @Transactional(readOnly = true)
    public Page<CommunityPost> getCommunityFeed(
            PostType postType,
            Pageable pageable
    ) {
        if (postType == null) {
            return postRepository.findAllByOrderByCreatedAtDesc(pageable);
        } else {
            return postRepository.findByPostTypeOrderByCreatedAtDesc(postType.name(), pageable);
        }
    }

    /**
     * Retrieves a post by its ID and increments its view count.
     *
     * @param postId the ID of the post to retrieve
     * @return the retrieved post
     */
    @Transactional
    public CommunityPost getPostAndIncrementViews(Long postId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        post.setViewsCount(post.getViewsCount() + 1);
        return post;
    }

    /**
     * Creates a new post.
     *
     * @param request the request object containing post details
     * @param user    the user creating the post
     * @return the created post
     */
    @Transactional
    public CommunityPost createPost(
            CreateCommunityPostRequestDto request,
            User user
    ) {
        CommunityPost post = communityPostMapper.toEntity(request);

        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());
        post.setPostType(request.getPostType().name());
        post.setLikesCount(0);
        post.setCommentsCount(0);
        post.setViewsCount(0);
        post.setCategories(categoryService.getCategoriesBySlugs(new java.util.HashSet<>(request.getCategorySlugs())));
        return postRepository.save(post);
    }

    @Transactional
    public CommunityPost updatePost(
            Long postId,
            UpdateCommunityPostRequestDto request,
            User user
    ) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException("You are not authorized to update this post");
        }

        if (request.getTitle() != null) {
            post.setTitle(request.getTitle());
        }
        if (request.getContent() != null) {
            post.setContent(request.getContent());
        }
        if (request.getPostType() != null) {
            post.setPostType(request.getPostType().name());
        }
        if (request.getCategorySlugs() != null) {
            Set<Category> categories = categoryService.getCategoriesBySlugs(new HashSet<>(request.getCategorySlugs()));
            post.setCategories(categories);
        }
        return post;
    }

    @Transactional
    public void deletePost(
            Long postId,
            User user
    ) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException("You are not authorized to delete this post");
        }

        postRepository.delete(post);
    }

    /**
     * Adds a comment to a post.
     *
     * @param postId  the ID of the post to which to add the comment
     * @param content the content of the comment
     * @param user    the user adding the comment
     * @return the added comment
     */
    @Transactional
    public Comment addComment(
            Long postId,
            String content,
            User user
    ) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        Comment comment = new Comment();

        comment.setContent(content);
        comment.setPost(post);
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());

        Comment saved = commentRepository.save(comment);
        post.setCommentsCount(post.getCommentsCount() + 1);
        return saved;
    }

    /**
     * Retrieves the comments for a given post.
     *
     * @param post the post for which to retrieve comments
     * @return a list of comments for the post
     */
    @Transactional(readOnly = true)
    public List<Comment> getComments(CommunityPost post) {
        return commentRepository.findByPostOrderByCreatedAtAsc(post);
    }

    @Transactional
    public void deleteComment(
            Long commentId,
            User user
    ) {
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        boolean isPostOwner = comment.getPost().getUser().getId().equals(user.getId());
        boolean isCommentOwner = comment.getUser().getId().equals(user.getId());

        if (!isPostOwner && !isCommentOwner) {
            throw new ForbiddenException("You are not authorized to delete this comment");
        }

        CommunityPost post = comment.getPost();
        commentRepository.delete(comment);
        post.setCommentsCount(post.getCommentsCount() - 1);
    }

    @Transactional
    public Comment updateComment(
            Long commentId,
            String content,
            User user
    ) {
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException("You are not authorized to update this comment");
        }
        comment.setContent(content);
        return comment;

    }

}

