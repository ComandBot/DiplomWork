package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.MyUserDetailsManager;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.RegisterDtoMapperService;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final MyUserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final RegisterDtoMapperService registerDtoMapperService;

    public AuthServiceImpl(@Qualifier(value = "myUserDetailsManager") MyUserDetailsManager manager,
                           PasswordEncoder passwordEncoder, RegisterDtoMapperService registerDtoMapperService) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.registerDtoMapperService = registerDtoMapperService;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        if (manager.userExists(registerDto.getUsername())) {
            return false;
        }
        UserEntity userEntity = registerDtoMapperService.mappingToEntity(registerDto);
        manager.setUserEntity(userEntity);
        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .username(userEntity.getEmail())
                        .password(userEntity.getPassword())
                        .roles(userEntity.getRole().name())
                        .build());
        return true;
    }

}
