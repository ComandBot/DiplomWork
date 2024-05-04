package ru.skypro.homework.mapper.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapperService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class CommentMapperServiceImpl implements CommentMapperService {

    @Override
    public CommentDto mappingToDto(CommentEntity entity) {
        CommentDto commentDto = new CommentDto();
        UserEntity userEntity = entity.getAdEntity().getUser();
        commentDto.setAuthor(userEntity.getId());
        commentDto.setAuthorImage(userEntity.getImage());
        commentDto.setAuthorFirstName(userEntity.getFirstName());
        long createAt = entity.getCreatedAt().toInstant(ZoneOffset.UTC).getEpochSecond();
        commentDto.setCreatedAt(createAt);
        commentDto.setPk(entity.getId());
        commentDto.setText(entity.getText());
        return commentDto;
    }

    @Override
    public CommentEntity mappingToEntity(CommentDto dto) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(dto.getPk());
        commentEntity.setText(dto.getText());
        commentEntity.setCreatedAt(LocalDateTime.ofEpochSecond(dto.getCreatedAt(), 0, ZoneOffset.UTC));
        return commentEntity;
    }
}
