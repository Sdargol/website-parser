package com.sdargol.controller;

import com.sdargol.dto.SiteDTO;
import com.sdargol.dto.SiteDTOBase;
import com.sdargol.dto.request.ParseSiteRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IParserController {
    void startParseSite(ParseSiteRequest parseSiteRequest);

    ResponseEntity<List<SiteDTOBase>> getAllSite();

    ResponseEntity<SiteDTO> getSiteInfo(Integer id);

    Integer deleteSite(Integer id);
}
