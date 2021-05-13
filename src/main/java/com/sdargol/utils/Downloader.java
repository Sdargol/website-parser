package com.sdargol.utils;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public final class Downloader {

    //генерируем из url имя файла на диске
    @Deprecated
    private String urlToFileName(String url) {
        StringBuilder fileName = new StringBuilder(url.replaceAll("https://", "")
                .replaceAll("/", "_"));

        if (!fileName.substring(fileName.length() - 1).equals("_")) {
            fileName.append("_");
        }

        String uuid = UUID.randomUUID().toString();

        fileName.append(uuid);
        fileName.append(".html");

        return fileName.toString();
    }

    //генерация полного пути к файлу для сохранения
    private String generateSavePath(String fileName){
        return StorageFolderUtil.getDefaultPath() + "\\" +
                fileName;
    }

    //загружаем страницу и сохраняем на диск
    public Path download(Pair<? extends String, ? extends String> pair) {
        String savePath = generateSavePath(pair.getSecond());

        try (BufferedInputStream bis = new BufferedInputStream(
                new URL(pair.getFirst()).openStream());
             FileOutputStream fis = new FileOutputStream(savePath)
        ) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = bis.read(dataBuffer, 0, 1024)) != -1) {
                fis.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Paths.get(savePath);
    }
}

