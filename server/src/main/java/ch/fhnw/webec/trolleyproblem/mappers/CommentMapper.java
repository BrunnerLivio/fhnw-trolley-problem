package ch.fhnw.webec.trolleyproblem.mappers;

import ch.fhnw.webec.trolleyproblem.dtos.CommentDto;
import ch.fhnw.webec.trolleyproblem.entities.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    List<CommentDto> commentEntityToCommentDtoList(List<CommentEntity> comments);
}
