package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.AvatarRepository;
import ru.skypro.homework.service.AvatarService;
import ru.skypro.homework.service.UserService;

@Service
public class AvatarServiceImpl implements AvatarService {
    @Value("${users.avatar.dir.path}")
    private final String avatarsDir;
    private final AvatarRepository avatarRepository;
    private final UserService userService;

    public AvatarServiceImpl(String avatarsDir, AvatarRepository avatarRepository, UserService userService) {
        this.avatarsDir = avatarsDir;
        this.avatarRepository = avatarRepository;
        this.userService = userService;
    }
}
