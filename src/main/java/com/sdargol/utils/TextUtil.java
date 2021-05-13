package com.sdargol.utils;

import com.sdargol.dto.WordDataDTO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TextUtil {
    public static Map<String, Integer> getUniqueWords(List<? extends String> strings){
        return  strings.stream()
                .filter(str -> !str.equals(""))
                .collect(
                        (Supplier<HashMap<String, Integer>>) HashMap::new,
                        (map, item) -> map.merge(item, 1, Integer::sum),
                        HashMap::putAll
                );
    }

    public static List<WordDataDTO> uniqueWordsToWordDataList(Map<? extends String, ? extends Integer> uniqueWords){
        var uniqueWordsEntries = uniqueWords.entrySet();
        return uniqueWordsEntries.stream()
                .map(e -> new WordDataDTO(e.getKey(), e.getValue())).collect(Collectors.toList());
    }

    @Deprecated
    public static byte[] stringToArrayByte(String str){
        return str.getBytes(StandardCharsets.UTF_8);
    }

    @Deprecated
    public static String buildStringOfArrayByte(byte[] strBytes){
        return new String(strBytes, StandardCharsets.UTF_8);
    }

    //генерируем из url имя файла на диске
    //использовать данный метод только один раз для конкретной веб-ссылки
    //например при создании базовой сущности классом SiteFactory
    public static String urlToFileName(String url) {
        System.out.println("вызов");
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

    //читаем файл по заданному пути и превращаем его в строку
    public static StringBuilder readFile(Path path) throws IOException {
        List<String> fileText = Files.readAllLines(path);
        StringBuilder text = new StringBuilder();
        fileText.forEach(text::append);
        return text;
    }
}
