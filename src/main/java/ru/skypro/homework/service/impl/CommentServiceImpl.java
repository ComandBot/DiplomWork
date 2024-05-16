package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapperService;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CommentMapperService commentMapperService;

    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository, CommentMapperService commentMapperService) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.commentMapperService = commentMapperService;
    }

    @Override
    public CommentsDto getComments(int id) {
        List<CommentEntity> commentEntities = commentRepository.findAllByAdEntity_Id(id);
        if (commentEntities == null || commentEntities.isEmpty()) {
            return null;
        }
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCount(commentEntities.size());
        commentsDto.setResults(commentEntities.stream()
                .map(commentMapperService::mappingToDto)
                .collect(Collectors.toList()));
        return commentsDto;
    }

    @Override
    public CommentDto addComment(int id, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        Optional<AdEntity> adEntityOptional = adRepository.findById(id);
        if (adEntityOptional.isEmpty()) {
            return null;
        }
        AdEntity adEntity = adEntityOptional.get();
        UserEntity userEntity = adEntity.getUser();
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(createOrUpdateCommentDto.getText());
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        commentEntity.setCreatedAt(date);
        commentEntity.setAdEntity(adEntity);
        commentRepository.save(commentEntity);
        return commentMapperService.mappingToDto(commentEntity);
    }

    @Override
    public CommentDto deleteComment(int adId, int commentId) {
        Optional<CommentEntity> commentEntityOptional = commentRepository.findByAdEntity_IdAndId(adId, commentId);
        if (commentEntityOptional.isEmpty()) {
            return null;
        }
        CommentEntity commentEntity = commentEntityOptional.get();
        commentRepository.delete(commentEntity);
        return commentMapperService.mappingToDto(commentEntity);
    }

    @Override
    public CommentDto updateComment(int adId, int commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        Optional<CommentEntity> commentEntityOptional =
                commentRepository.findByAdEntity_IdAndId(adId, commentId);
        if (commentEntityOptional.isEmpty()) {
            return null;
        }
        CommentEntity commentEntity = commentEntityOptional.get();
        commentEntity.setText(createOrUpdateCommentDto.getText());
        commentRepository.save(commentEntity);
        return commentMapperService.mappingToDto(commentEntity);
    }
}
