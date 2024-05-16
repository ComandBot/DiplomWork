package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

public interface CommentService {

    CommentsDto getComments(int id);

    CommentDto addComment(int id, CreateOrUpdateCommentDto createOrUpdateCommentDto);

    CommentDto deleteComment(int adId, int commentId);
}
