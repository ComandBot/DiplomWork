package ru.skypro.homework.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class WorkWithFilesUtils {

    public static String loadImage(MultipartFile file, String pathDir, int id, TypeImage image) throws IOException {
        String fileName = image.toString() + id + "." + getExtension(file.getOriginalFilename());
        Path filePath = Path.of(pathDir, fileName);
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)){
            bis.transferTo(bos);
        }
        return fileName;
    }

    private static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static void deleteFile(String url) throws IOException {
        Path path = Path.of(url);
        Files.delete(path);
    }

    public static byte[] getImage(String id, String pathDir) {
        Path filePath = Path.of(pathDir + "\\" + id);
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            bis.transferTo(baos);
            return baos.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }
}
