package ru.skypro.homework.mapper.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapperService;
import ru.skypro.homework.repository.AdRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class CommentMapperServiceImpl implements CommentMapperService {

    private final AdRepository adRepository;

    public CommentMapperServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public Comment mappingToDto(CommentEntity entity) {
        Comment comment = new Comment();
        UserEntity userEntity = entity.getAdEntity().getUser();
        comment.setAuthor(userEntity.getId());
        comment.setAuthorImage(userEntity.getImage());
        comment.setAuthorFirstName(userEntity.getFirstName());
        long createAt = entity.getCreatedAt().toInstant(ZoneOffset.UTC).getEpochSecond();
        comment.setCreatedAt(createAt);
        comment.setPk(entity.getId());
        comment.setText(entity.getText());
        return comment;
    }

    @Override
    public CommentEntity mappingToEntity(Comment dto) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(dto.getPk());
        commentEntity.setText(dto.getText());
        commentEntity.setCreatedAt(LocalDateTime.ofEpochSecond(dto.getCreatedAt(), 0, ZoneOffset.UTC));
        AdEntity adEntity = adRepository.findByCommentId(dto.getPk());
        commentEntity.setAdEntity(adEntity);
        return commentEntity;
    }
}
