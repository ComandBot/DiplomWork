package ru.skypro.homework.mapper.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.mapper.AdMapperService;
import ru.skypro.homework.mapper.AdsMapperService;
import ru.skypro.homework.repository.AdRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdsMapperServiceImpl implements AdsMapperService {

    private final AdRepository adRepository;
    private final AdMapperService adMapperService;

    public AdsMapperServiceImpl(AdRepository repository, AdMapperService adMapperService) {
        this.adRepository = repository;
        this.adMapperService = adMapperService;
    }

    @Override
    public Ads mapperAds() {
        Ads ads = new Ads();
        List<Ad> adList = adRepository.findAll()
                .stream()
                .map(adMapperService::mappingToDto)
                .collect(Collectors.toList());
        ads.setCount(adList.size());
        ads.setResult(adList);
        return ads;
    }
}
