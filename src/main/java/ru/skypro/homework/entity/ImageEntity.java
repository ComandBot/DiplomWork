package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String nameImage;
}
