package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.service.CommentService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentsController {

//    private final CommentService commentService;

//    public CommentsController(CommentService commentService) {
//        this.commentService = commentService;
//    }


    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComment(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> setComment(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        return ResponseEntity.ok().build();
    }
}
