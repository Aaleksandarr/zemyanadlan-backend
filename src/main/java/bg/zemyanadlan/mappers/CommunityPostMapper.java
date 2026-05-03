package bg.zemyanadlan.mappers;

import bg.zemyanadlan.dtos.CommunityPostDetailsDto;
import bg.zemyanadlan.dtos.CommunityPostFeedDto;
import bg.zemyanadlan.dtos.CreateCommunityPostRequestDto;
import bg.zemyanadlan.entities.CommunityPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommunityPostMapper {

    @Mapping(target = "excerpt",
            expression = "java(post.getContent().substring(0, Math.min(200, post.getContent().length())))")
    @Mapping(target = "authorName", source = "user.username")
    CommunityPostFeedDto toFeedDto(CommunityPost post);


    @Mapping(target = "authorName", source = "user.username")
    @Mapping(target = "comments", ignore = true)
    CommunityPostDetailsDto toDetailsDto(CommunityPost post);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "viewsCount", ignore = true)
    @Mapping(target = "commentsCount", ignore = true)
    @Mapping(target = "likesCount", ignore = true)
    CommunityPost toEntity(CreateCommunityPostRequestDto detailsDto);
}
