package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.service.impl.UserServiceImpl;

@RestController
public class ImageController {
    private final UserServiceImpl userService;

    public ImageController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Operation(
            tags = "Картинки"
    )
    @GetMapping(value = "src/main/resources/images/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"})
    public ResponseEntity<?> getMeImage(@PathVariable String id) {
        return ResponseEntity.ok(userService.getImage(id));
    }
}
