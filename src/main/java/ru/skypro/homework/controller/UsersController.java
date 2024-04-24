package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    public String setPasswordUser() {
        return null;
    }

    @GetMapping("/me") // todo будет возвращать ResponseEntity, временно void
    public void getUserInfo() {

    }

    @PatchMapping("/me") // todo будет возвращать ResponseEntity, временно void
    public void updateUser() {

    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // todo будет возвращать ResponseEntity, временно void
    public void updateUserAvatar() {

    }
}
