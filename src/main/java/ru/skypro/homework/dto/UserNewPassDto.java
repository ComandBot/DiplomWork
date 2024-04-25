package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class UserNewPassDto {
   private String currentPassword;
   private String newPassword;
}
