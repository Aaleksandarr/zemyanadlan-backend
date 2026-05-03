package bg.zemyanadlan.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String authorName;

}
