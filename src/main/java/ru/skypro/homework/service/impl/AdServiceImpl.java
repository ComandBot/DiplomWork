package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapperService;
import ru.skypro.homework.mapper.CreateOrUpdateService;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.utils.WriteImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {
    @Value(value = "${photo.dir.path}")
    private String photoDir;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapperService adMapperService;
    private final CreateOrUpdateService createOrUpdateService;

    public AdServiceImpl(AdRepository adRepository, UserRepository userRepository, AdMapperService adMapperService, CreateOrUpdateService createOrUpdateService) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.adMapperService = adMapperService;
        this.createOrUpdateService = createOrUpdateService;
    }

    @Override
    public AdsDto getAllAds() {
        List<AdDto> result = adRepository.findAll().stream()
                .map(adMapperService::mappingToDto).collect(Collectors.toList());
        AdsDto adsDto = new AdsDto();
        adsDto.setCount(result.size());
        adsDto.setResult(result);
        return adsDto;
    }

    @Override
    public AdDto addAd(MultipartFile image, CreateOrUpdateAdDto properties) throws IOException {
        String username = getUserName();
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(username);
        if (userEntityOptional.isEmpty()) {
            return null;
        }
        UserEntity userEntity = userEntityOptional.get();
        AdEntity adEntity = createOrUpdateService.mappingToEntity(properties);
        adEntity.setUser(userEntity);
        adRepository.save(adEntity);
        Path path = WriteImage.loadImage(image, photoDir, adEntity.getId());
        adEntity.setImage(path.toString());
        adRepository.save(adEntity);
        return adMapperService.mappingToDto(adEntity);
    }

    @Override
    public ExtendedAdDto getAd(int id) {
        Optional<AdEntity> adEntityOptional = adRepository.findById(id);
        if (adEntityOptional.isEmpty()) {
            return null;
        }
        AdEntity adEntity = adEntityOptional.get();
        ExtendedAdDto result = new ExtendedAdDto();
        result.setPk(adEntity.getId());
        result.setAuthorFirstName(adEntity.getUser().getFirstName());
        result.setAuthorLastName(adEntity.getUser().getLastName());
        result.setDescription(adEntity.getDescription());
        result.setEmail(adEntity.getUser().getEmail());
        result.setImage(adEntity.getImage());
        result.setPhone(adEntity.getUser().getPhone());
        result.setPrice(adEntity.getPrice());
        result.setTitle(adEntity.getTitle());
        return result;
    }

    private String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
