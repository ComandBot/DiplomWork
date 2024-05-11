package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

@Service
public class AdServiceImpl implements AdService {
    @Value(value = "${photo.dir.path}")
    private String photoDir;
    private final AdRepository adRepository;
    private final UserService userService;

    public AdServiceImpl(AdRepository adRepository, UserService userService) {
        this.adRepository = adRepository;
        this.userService = userService;
    }
}
