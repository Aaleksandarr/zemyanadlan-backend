package bg.zemyanadlan.dtos;

import bg.zemyanadlan.entities.PostType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCommunityPostRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private PostType postType;

    private String thumbnailUrl;

}
