package com.sdargol.service;

import com.sdargol.dto.*;
import com.sdargol.entities.Site;
import com.sdargol.entities.SiteFactory;
import com.sdargol.repositories.SiteRepository;
import com.sdargol.utils.Downloader;
import com.sdargol.utils.TextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParserService implements IParserService {
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private final SiteRepository siteRepository;
    private final Downloader downloader;

    private final Logger logger = LoggerFactory.getLogger(ParserService.class);

    @Autowired
    public ParserService(ThreadPoolTaskExecutor threadPoolTaskExecutor, SiteRepository siteRepository, Downloader downloader) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.siteRepository = siteRepository;
        this.downloader = downloader;
    }

    @Override
    public void startParseSite(String url) {
        //создали имя файла и больше не меняем
        String fileName = TextUtil.urlToFileName(url);
        logger.info("Успешно создали имя");

        //создали базовую сущность и устанавливаем name и fileName
        Site site = new SiteFactory().create();
        site.setName(url);
        site.setFileName(fileName);

        //сохраняем базовую сущность с нужными именами и статусом PROCESSING
        siteRepository.save(site);
        logger.info("Сущность сохранена");

        ParserTaskBuilder<Pair<String, Path>> parserTaskBuilder = new ParserTaskBuilder<>();

        Pair<String, String> urlAndFileName =  Pair.of(url,fileName);

        parserTaskBuilder.setDownloadTask(() -> ParserSteps.downloadPage(downloader,urlAndFileName));
        parserTaskBuilder.setParseTask((p) -> ParserSteps.parsePageFile(p,siteRepository));
        parserTaskBuilder.build(threadPoolTaskExecutor);
    }

    @Override
    public List<SiteDTOBase> getAllSite() {
        List<Site> allSites = siteRepository.findAll();
        List<SiteDTOBase> siteDTOBases = new ArrayList<>();

        allSites.forEach((s) -> {
            siteDTOBases.add(SiteDTOBuilder.getInstance()
                    .setId(s.getId())
                    .setName(s.getName())
                    .setUrlGetFile(s.getFileName())
                    .setStatus(s.getStatus())
                    .buildBase());
        });

        return siteDTOBases;
    }

    @Override
    public SiteDTO getSiteInfo(Integer id) {
        Site site = siteRepository.getById(id);
        return SiteEntityToDTO.convert(site);
    }

    @Override
    public Integer deleteSite(Integer id) {
        siteRepository.deleteById(id);
        return id;
    }
}
