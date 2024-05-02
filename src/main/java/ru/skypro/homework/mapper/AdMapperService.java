package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entity.AdEntity;

public interface AdMapperService {
    Ad mappingToDto(AdEntity entity);

    AdEntity mappingToEntity(Ad dto);
}
