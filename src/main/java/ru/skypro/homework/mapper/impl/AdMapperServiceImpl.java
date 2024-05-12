package ru.skypro.homework.mapper.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.mapper.AdMapperService;

@Service
public class AdMapperServiceImpl implements AdMapperService {

    @Override
    public AdDto mappingToDto(AdEntity entity) {
        AdDto result = new AdDto();
        result.setAuthor(entity.getUser().getId());
        result.setImage(entity.getImage());
        result.setPk(entity.getId());
        result.setPrice(entity.getPrice());
        result.setTitle(entity.getTitle());
        return result;
    }

    @Override
    public AdEntity mappingToEntity(AdDto dto) {
        AdEntity adEntity = new AdEntity();
        adEntity.setId(dto.getPk());
        adEntity.setImage(dto.getImage());
        adEntity.setPrice(dto.getPrice());
        adEntity.setTitle(dto.getTitle());
        return adEntity;
    }

}
