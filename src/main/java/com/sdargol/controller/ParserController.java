package com.sdargol.controller;

import com.sdargol.dto.SiteDTO;
import com.sdargol.dto.SiteDTOBase;
import com.sdargol.dto.request.ParseSiteRequest;
import com.sdargol.service.IParserService;
import com.sdargol.service.ParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ParserController implements IParserController {
    private final IParserService parserService;
    private final Logger logger = LoggerFactory.getLogger(ParserController.class);

    @Autowired
    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v1/parser")
    public void startParseSite(@Valid @RequestBody ParseSiteRequest parseSiteRequest) {
        parserService.startParseSite(parseSiteRequest.getUrl());
    }

    @Override
    @GetMapping("/api/v1/parser")
    public ResponseEntity<List<SiteDTOBase>> getAllSite() {
        return ResponseEntity.ok(parserService.getAllSite());
    }

    @Override
    @GetMapping("/api/v1/parser/{id}")
    public ResponseEntity<SiteDTO> getSiteInfo(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(parserService.getSiteInfo(id));
    }

    @Override
    @DeleteMapping("/api/v1/parser/{id}")
    public Integer deleteSite(@PathVariable("id") Integer id) {
        return parserService.deleteSite(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleValidException(MethodArgumentNotValidException e){
        logger.warn(e.getMessage());
    }
}
