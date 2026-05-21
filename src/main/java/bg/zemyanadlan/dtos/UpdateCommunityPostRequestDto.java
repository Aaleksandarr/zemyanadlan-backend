package bg.zemyanadlan.dtos;

import bg.zemyanadlan.entities.PostType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
public class UpdateCommunityPostRequestDto {

    private String title;
    private String content;
    private String thumbnailUrl;
    private PostType postType;
    private List<String> categorySlugs;

}
