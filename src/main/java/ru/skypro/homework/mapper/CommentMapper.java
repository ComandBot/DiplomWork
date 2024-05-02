package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

@Component
public class CommentMapper {

    // Entity -> Dto todo добавить дату
    public Comment toCommentDto(CommentEntity commentEntity) {
        Comment commentDto = new Comment();
        commentDto.setAuthor(commentEntity.getUser().getId());
        commentDto.setAuthorFirstName(commentEntity.getUser().getFirstName());
        commentDto.setAuthorImage(String.valueOf(commentEntity.getUser().getAvatar().getId()));
        commentDto.setPk(commentEntity.getId());
        commentDto.setText(commentEntity.getText());
        return commentDto;
    }

}
