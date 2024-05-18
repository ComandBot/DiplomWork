package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AdEntity> adEntities;

    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity imageEntity;
}
