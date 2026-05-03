package bg.zemyanadlan.dtos;

import bg.zemyanadlan.entities.PostType;
import bg.zemyanadlan.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class CommunityPostFeedDto {
    private Long id;
    private String title;
    private String excerpt;
    private PostType postType;
    private LocalDateTime createdAt;
    private String authorName;
    private int likesCount;
    private int commentsCount;
    private int viewsCount;
    private String thumbnailUrl;
}
