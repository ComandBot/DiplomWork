package ru.skypro.homework.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class WorkWithFilesUtils {
    public static Path loadImage(MultipartFile file, String pathDir, int id) throws IOException {
        Path filePath = Path.of(pathDir, id + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)){
            bis.transferTo(bos);
        }
        return filePath;
    }

    private static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static void deleteFile(String url) throws IOException {
        Path path = Path.of(url);
        Files.delete(path);
    }
}
