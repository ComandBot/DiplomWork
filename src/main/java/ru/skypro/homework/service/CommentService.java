package ru.skypro.homework.service;

import org.springframework.web.bind.annotation.PathVariable;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

public interface CommentService {

    CommentsDto getComments(int id);

    CommentDto addComment(int id, CreateOrUpdateCommentDto createOrUpdateCommentDto);
}
