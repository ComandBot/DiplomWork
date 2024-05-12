package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;

import java.util.Optional;

public interface AdService {
    AdDto addAd(CreateOrUpdateAdDto newAd, MultipartFile photo);

    AdsDto findAllAds();
    Optional<AdDto> findAdsByAuthUser();
    AdDto updateAd(CreateOrUpdateAdDto newAd);

    void updateAdPhoto(MultipartFile file);
    void deleteAd(int id);


}
