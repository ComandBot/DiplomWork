package ru.skypro.homework.service;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

import java.io.IOException;

public interface AdService {
    AdsDto getAllAds();

    AdDto addAd(MultipartFile image, CreateOrUpdateAdDto properties) throws IOException;

    ExtendedAdDto getAd(int id);
}
