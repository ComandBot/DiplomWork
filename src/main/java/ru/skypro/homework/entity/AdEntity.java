package ru.skypro.homework.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name= "ads")
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String image;
    private int price;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "adEntity", fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntities;
}
