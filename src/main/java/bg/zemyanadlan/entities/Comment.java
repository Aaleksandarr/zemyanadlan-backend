package bg.zemyanadlan.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Represents a comment on a community post, including the author and associated post.
 */
@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * The user who made the comment.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The community post this comment belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "community_post_id")
    private CommunityPost post;

}
