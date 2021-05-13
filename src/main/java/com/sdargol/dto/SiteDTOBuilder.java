package com.sdargol.dto;

import com.sdargol.entities.Status;

public class SiteDTOBuilder {
    private Integer id;
    private String name;
    private String urlGetFile;
    private Status status;
    private StatisticsDTO statistics;

    private SiteDTOBuilder() {
    }

    private static String generateUrlGetFile(String fileName){
        return "/download/" + fileName;
    }

    public static SiteDTOBuilder getInstance(){
        return new SiteDTOBuilder();
    }

    public SiteDTOBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public SiteDTOBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SiteDTOBuilder setUrlGetFile(String fileName) {
        this.urlGetFile = generateUrlGetFile(fileName);
        return this;
    }

    public SiteDTOBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public SiteDTOBuilder setStatistics(StatisticsDTO statistics) {
        this.statistics = statistics;
        return this;
    }

    public SiteDTO build() {
        return new SiteDTO(id,name,urlGetFile,status,statistics);
    }

    public SiteDTOBase buildBase() {
        return new SiteDTOBase(id,name,urlGetFile,status);
    }
}
