package bg.zemyanadlan.mappers;

import bg.zemyanadlan.dtos.CommentDto;
import bg.zemyanadlan.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "authorName", source = "user.username")
    CommentDto toDto(Comment comment);
}
