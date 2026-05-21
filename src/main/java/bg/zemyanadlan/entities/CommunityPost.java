package bg.zemyanadlan.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a community post in the application, such as recipes, tips, or stories.
 * This entity is mapped to the "community_posts" table and includes metadata like
 * likes, comments, and views for engagement tracking.
 */
@Getter
@Setter
@Entity
@Table(name = "community_posts")
public class CommunityPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "post_type", length = 20)
    private String postType;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * The user who authored the community post.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The set of categories associated with this post, allowing for many-to-many relationships.
     */
    @ManyToMany
    @JoinTable(
            name = "post_categories",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Comment> comments = new HashSet<>();

    private int likesCount;
    private int commentsCount;
    private int viewsCount;
    private String thumbnailUrl;
}
