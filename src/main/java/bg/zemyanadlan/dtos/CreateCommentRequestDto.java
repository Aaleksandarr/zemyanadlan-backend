package bg.zemyanadlan.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCommentRequestDto {
    @NotBlank
    private String content;
}

