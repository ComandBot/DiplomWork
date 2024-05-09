package ru.skypro.homework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

@Service
public class MyUserDetailsManager implements UserDetailsManager {

    private final UserRepository userRepository;
    private UserEntity userEntity;
    private boolean isVerifyPassword = false;

    private PasswordEncoder passwordEncoder;

    public MyUserDetailsManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserDetails user) {
        userEntity.setPassword(user.getPassword());
        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(getUserName());
        if (userEntityOptional.isEmpty()) {
            isVerifyPassword = false;
            return;
        }
        UserEntity userEntity = userEntityOptional.get();
        if (!passwordEncoder.matches(oldPassword, userEntity.getPassword())) {
            isVerifyPassword = false;
            return;
        }
        userEntity.setPassword(passwordEncoder.encode(newPassword));
        isVerifyPassword = true;
        userRepository.save(userEntity);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByEmail(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(username);
        if (userEntityOptional.isEmpty()) {
            throw new UsernameNotFoundException("Нет пользоватля с именем " + username);
        }
        UserEntity userEntity = userEntityOptional.get();
        return User.builder()
                .username(username)
                .password(userEntity.getPassword())
                .roles(userEntity.getRole().name())
                .build();
    }

    private String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public boolean isVerifyPassword() {
        return isVerifyPassword;
    }

    public void setVerifyPassword(boolean verifyPassword) {
        isVerifyPassword = verifyPassword;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
