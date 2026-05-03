package bg.zemyanadlan.dtos;

import bg.zemyanadlan.entities.PostType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommunityPostDetailsDto {
    private Long id;
    private String title;
    private String content;
    private PostType postType;
    private LocalDateTime createdAt;
    private String authorName;
    private int likesCount;
    private int commentsCount;
    private int viewsCount;
    private String thumbnailUrl;
    private List<CommentDto> comments;
}
