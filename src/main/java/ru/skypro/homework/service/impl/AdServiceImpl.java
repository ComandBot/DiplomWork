package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.mapper.AdMapperService;

import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {
//    @Value(value = "${photo.dir.path}")
//    private String photoDir;
    private final AdRepository adRepository;
    private final UserService userService;

    private final AdMapperService adMapperService;


    public AdServiceImpl(AdRepository adRepository, UserService userService, AdMapperService adMapperService) {
        this.adRepository = adRepository;
        this.userService = userService;
        this.adMapperService = adMapperService;
    }


    @Override
    public AdDto addAd(CreateOrUpdateAdDto newAd) {
        return null;
    }

    @Override
    public Optional<AdDto> findAllAds() {
        return Optional.empty();
    }

    @Override
    public Optional<AdDto> findAdsByAuthUser() {
        return Optional.empty();
    }

    @Override
    public AdDto updateAd(CreateOrUpdateAdDto newAd) {
        return null;
    }

    @Override
    public void deleteAd() {

    }
}
