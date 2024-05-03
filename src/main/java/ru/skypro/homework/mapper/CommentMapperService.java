package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

public interface CommentMapperService {
    Comment mappingToDto(CommentEntity entity);

    CommentEntity mappingToEntity(Comment dto);
}
