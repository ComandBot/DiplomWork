package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;

import java.util.Optional;

public interface AdService {
    AdDto addAd(CreateOrUpdateAdDto newAd);
    Optional<AdDto> findAllAds();
    Optional<AdDto> findAdsByAuthUser();
    AdDto updateAd(CreateOrUpdateAdDto newAd);
    void deleteAd();


}
