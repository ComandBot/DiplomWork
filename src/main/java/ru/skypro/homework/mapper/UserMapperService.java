package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

public interface UserMapperService {
    User mappingToDto(UserEntity entity);

    UserEntity mappingToEntity(User dto);
}
