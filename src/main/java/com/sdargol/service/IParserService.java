package com.sdargol.service;

import com.sdargol.dto.SiteDTO;
import com.sdargol.dto.SiteDTOBase;

import java.util.List;

public interface IParserService {
    void startParseSite(String url);

    List<SiteDTOBase> getAllSite();

    SiteDTO getSiteInfo(Integer id);

    Integer deleteSite(Integer id);
}
