package com.sdargol.service;

import com.sdargol.dto.WordDataDTO;
import com.sdargol.entities.Site;
import com.sdargol.entities.Status;
import com.sdargol.repositories.SiteRepository;
import com.sdargol.utils.ConverterUtil;
import com.sdargol.utils.Downloader;
import com.sdargol.utils.ParserBuilder;
import com.sdargol.utils.TextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ParserSteps {

    private static final Logger logger = LoggerFactory.getLogger(ParserSteps.class);

    public static Pair<String, Path> downloadPage(Downloader downloader, Pair<? extends String, ? extends String> pair){
        //качаем файл и сохраняем
        Path filePath = downloader.download(pair);
        return Pair.of(pair.getFirst(),filePath);
    }

    public static void parsePageFile(Pair<? extends String, ? extends Path> pair, SiteRepository siteRepository){
        //читаем загруженный файл и превращаем его в строку
        StringBuilder content = new StringBuilder("empty");
        try {
            content = TextUtil.readFile(pair.getSecond());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Файл прочитан");

        //удаляем все лишнее
        List<String> parserResult = ParserBuilder.of(content)
                .removeScript()
                .removeStyle()
                .removeHtmlTag()
                .trimTab()
                .trimSpace()
                .toList();
        logger.info("Удалили все лишнее");

        //считаем слова
        Map<String, Integer> uniqueWords = TextUtil.getUniqueWords(parserResult);
        logger.info("Посчитали слова");

        //перегоняем из map в конкретные сущности
        List<WordDataDTO> wordDataList = TextUtil.uniqueWordsToWordDataList(uniqueWords);
        logger.info("Получили сущности");

        //начинаем процесс конвертации из list в массив byte
        byte[] bytes = ConverterUtil.toArrayByte(wordDataList);
        logger.info("Получили массив байт");

        //обновляем сущность
        Site site = siteRepository.findByName(pair.getFirst());
        site.setStatus(Status.FINISHED);
        site.getStatistics().setStats(bytes);
        site.getStatistics().setDate(new Date());
        site.getStatistics().setCountWord(wordDataList.size());
        siteRepository.save(site);
        logger.info("Обновили сущность");
    }
}
