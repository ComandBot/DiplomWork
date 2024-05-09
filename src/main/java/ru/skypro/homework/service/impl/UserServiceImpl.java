package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.config.MyUserDetailsManager;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapperService;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class UserServiceImpl implements UserService {

    @Value(value = "${avatars.dir.path}")
    private String pathDir;

    private final UserRepository userRepository;
    private final UserMapperService userMapperService;
    private final MyUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapperService userMapperService, MyUserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapperService = userMapperService;
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean setPassword(NewPasswordDto newPasswordDto) {
        userDetailsManager.setVerifyPassword(false);
        userDetailsManager.changePassword(newPasswordDto.getCurrentPassword(), newPasswordDto.getNewPassword());
        return userDetailsManager.isVerifyPassword();
    }

    public UserDto getUser() {
        String userName = getUserName();
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userName);
        if (userEntityOptional.isEmpty()) {
            return null;
        }
        UserEntity userEntity = userEntityOptional.get();
        return userMapperService.mappingToDto(userEntity);
    }

    public UpdateUserDto updateUser() {
        return null;
    }

    public void updateUserImage(MultipartFile file) throws IOException {
        String userName = getUserName();
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userName);
        if (userEntityOptional.isEmpty()) {
            return;
        }
        UserEntity userEntity = userEntityOptional.get();
        Path filePath = Path.of(pathDir, userEntity.getId() + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)){
            bis.transferTo(bos);
        }
        userEntity.setImage(filePath.toString());
        userRepository.save(userEntity);
    }

    private String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
