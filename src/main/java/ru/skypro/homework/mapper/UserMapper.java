package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

public class UserMapper {

// Entity -> Dto
    public User toUserDto(UserEntity userEntity){
        User userDto = new User();
        userDto.setId(userEntity.getId());
        userDto.setEmail(userEntity.getEmail());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setPhone(userEntity.getPhone());
        return userDto;
    }
// Dto -> Entity
    public UserEntity toUserEntity(User userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPhone(userDto.getPhone());
        return userEntity;
    }
}
