package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.config.MyUserDetailsService;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UpdateUserDtoMapperService;
import ru.skypro.homework.mapper.UserMapperService;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.utils.WriteImage;

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
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapperService userMapperService,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapperService = userMapperService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean setPassword(NewPasswordDto newPasswordDto) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(getUserName());
        if (userEntityOptional.isEmpty()) {
            return false;
        }
        UserEntity userEntity = userEntityOptional.get();
        if (!passwordEncoder.matches(newPasswordDto.getCurrentPassword(), userEntity.getPassword())) {
            return false;
        }
        userEntity.setPassword(passwordEncoder.encode(newPasswordDto.getNewPassword()));
        userRepository.save(userEntity);
        return true;
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

    public UpdateUserDto updateUser(UpdateUserDto updateUserDto) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(getUserName());
        if (userEntityOptional.isEmpty()) {
            return null;
        }
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setFirstName(updateUserDto.getFirstName());
        userEntity.setLastName(updateUserDto.getLastName());
        userEntity.setPhone(updateUserDto.getPhone());
        userRepository.save(userEntity);
        return updateUserDto;
    }

    public void updateUserImage(MultipartFile file) throws IOException {
        String userName = getUserName();
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userName);
        if (userEntityOptional.isEmpty()) {
            return;
        }
        UserEntity userEntity = userEntityOptional.get();
        Path path = WriteImage.loadImage(file, pathDir, userEntity.getId());
        userEntity.setImage(path.toString());
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
