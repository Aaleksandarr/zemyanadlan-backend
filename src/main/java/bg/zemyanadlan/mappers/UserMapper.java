package bg.zemyanadlan.mappers;

import bg.zemyanadlan.dtos.RegisterUserRequest;
import bg.zemyanadlan.dtos.UpdateUserRequest;
import bg.zemyanadlan.dtos.UserDto;
import bg.zemyanadlan.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
