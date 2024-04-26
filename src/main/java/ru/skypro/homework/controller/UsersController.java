package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserUpdDto;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UsersController {

//    private final UserUpdService userUpdService;
//
//    public UsersUpdController(UserUpdService userService) {
//        this.userUpdService = userService;
//    }

    @PostMapping("/set_password")
    public ResponseEntity<?> setPasswordUser(@RequestBody NewPassword userNewPassDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserInfo() {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me")
    public ResponseEntity<UserUpdDto> updateUser(@RequestBody UserUpdDto userUpdDto) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserAvatar(@RequestParam String image) {
        return ResponseEntity.ok().build();
    }
}
