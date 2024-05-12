package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.mapper.impl.CreateOrUpdateServiceImpl;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.mapper.AdMapperService;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AdServiceImpl implements AdService {
    @Value(value = "${photo.dir.path}")
    private String photoDir;
    private final AdRepository adRepository;
    private final UserService userService;

    private final AdMapperService adMapperService;
    private final CreateOrUpdateServiceImpl createOrUpdateService;


    public AdServiceImpl(AdRepository adRepository, UserService userService, AdMapperService adMapperService, CreateOrUpdateServiceImpl createOrUpdateService) {
        this.adRepository = adRepository;
        this.userService = userService;
        this.adMapperService = adMapperService;
        this.createOrUpdateService = createOrUpdateService;
    }


    @Override
    public AdDto addAd(CreateOrUpdateAdDto newAd, MultipartFile photo) {
        AdEntity adEntity = createOrUpdateService.mappingToEntity(newAd);
        adRepository.save(adEntity);

        return null;
    }

    @Override
    public AdsDto findAllAds() {
        List<AdDto> dtos = adRepository.findAll().stream()
                .map(entity -> adMapperService.mappingToDto(entity))
                .collect(Collectors.toList());
        return new AdsDto();
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
    public void updateAdPhoto(MultipartFile file) {
    }

    @Override
    public void deleteAd(int id) {
        adRepository.findById(id).orElseThrow(() -> new NotFoundException("Объявление не найдено"));
        adRepository.deleteById(id);
    }
}
