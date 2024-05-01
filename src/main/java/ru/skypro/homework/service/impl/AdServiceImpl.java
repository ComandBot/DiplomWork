package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.PhotoRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

@Service
public class AdServiceImpl implements AdService {
    @Value("${ads.photo.dir.path}")
    private final String photoDir;
    private final AdRepository adRepository;
    private final PhotoRepository photoRepository;
    private final UserService userService;

    public AdServiceImpl(String photoDir, AdRepository adRepository, PhotoRepository photoRepository, UserService userService) {
        this.photoDir = photoDir;
        this.adRepository = adRepository;
        this.photoRepository = photoRepository;
        this.userService = userService;
    }
}
