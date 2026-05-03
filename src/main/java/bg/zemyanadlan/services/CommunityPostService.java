package bg.zemyanadlan.services;

import bg.zemyanadlan.dtos.CreateCommentRequestDto;
import bg.zemyanadlan.dtos.CreateCommunityPostRequestDto;
import bg.zemyanadlan.entities.Comment;
import bg.zemyanadlan.entities.CommunityPost;
import bg.zemyanadlan.entities.PostType;
import bg.zemyanadlan.entities.User;
import bg.zemyanadlan.mappers.CommunityPostMapper;
import bg.zemyanadlan.repositories.CommentRepository;
import bg.zemyanadlan.repositories.CommunityPostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CommunityPostService {
    private final CommunityPostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommunityPostMapper communityPostMapper;

    @Transactional(readOnly = true)
    public Page<CommunityPost> getCommunityFeed(
            PostType postType,
            Pageable pageable
    ){
        if (postType == null){
            return postRepository.findAllByOrderByCreatedAtDesc(pageable);
        } else {
            return postRepository.findByPostTypeOrderByCreatedAtDesc(postType, pageable);
        }
    }

    @Transactional
    public CommunityPost getPostAndIncrementViews(Long postId){
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        post.setViewsCount(post.getViewsCount() + 1);
        return post;
    }

    @Transactional(readOnly = true)
    public List<Comment> getComments(CommunityPost post){
        return commentRepository.findByPostOrderByCreatedAtAsc(post);
    }

    @Transactional
    public Comment addComment(
            Long postId,
            String content,
            User user
    ){
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        Comment comment = new Comment();

        comment.setContent(content);
        comment.setPost(post);
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());

        Comment saved = commentRepository.save(comment);
        post.setCommentsCount(post.getCommentsCount() + 1);
        return saved;
    }

    @Transactional
    public CommunityPost createPost(
          CreateCommunityPostRequestDto request,
          User user
    ){
        CommunityPost post = communityPostMapper.toEntity(request);

        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());
        post.setLikesCount(0);
        post.setCommentsCount(0);
        post.setViewsCount(0);

        return postRepository.save(post);
    }
}
