package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.CommentService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentsController {
    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{id}/comments")
    public String setComment() {
        return null;
    }

    @GetMapping("/{id}/comments") // todo будет возвращать ResponseEntity, временно void
    public void getComment() {

    }

    @PatchMapping("/{adId}/comments/{commentId}") // todo будет возвращать ResponseEntity, временно void
    public void updateComment() {

    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment() {

    }
}
