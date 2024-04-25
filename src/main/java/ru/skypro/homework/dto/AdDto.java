package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdDto {
    Integer authorId;
    String image;
    Integer pk;
    Integer price;
    String title;
}
