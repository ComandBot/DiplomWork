package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UserInfoDto;
import ru.skypro.homework.dto.UserNewPassDto;
import ru.skypro.homework.dto.UserUpdDto;
import ru.skypro.homework.service.UserUpdService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UsersUpdController {

//    private final UserUpdService userUpdService;
//
//    public UsersUpdController(UserUpdService userService) {
//        this.userUpdService = userService;
//    }

    @PostMapping("/set_password")
    public ResponseEntity<?> setPasswordUser(@RequestBody UserNewPassDto userNewPassDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfoDto> getUserInfo() {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdDto userUpdDto) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // todo будет возвращать ResponseEntity, временно void
    public void updateUserAvatar() {

    }
}
