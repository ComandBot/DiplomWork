package ru.skypro.homework.mapper;

public interface MapperService<T, R> {
    R mappingToDto(T entity);

    T mappingToEntity(R dto);
}
