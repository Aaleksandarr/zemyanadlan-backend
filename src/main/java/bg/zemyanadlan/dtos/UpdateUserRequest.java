package bg.zemyanadlan.dtos;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String username;
    private String email;
}
