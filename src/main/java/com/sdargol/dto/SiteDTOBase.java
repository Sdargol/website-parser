package com.sdargol.dto;

import com.sdargol.entities.Status;

public class SiteDTOBase {
    private Integer id;
    private String name;
    private String urlGetFile;
    private Status status;

    public SiteDTOBase() {
    }

    public SiteDTOBase(Integer id, String name, String urlGetFile, Status status) {
        this.id = id;
        this.name = name;
        this.urlGetFile = urlGetFile;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlGetFile() {
        return urlGetFile;
    }

    public void setUrlGetFile(String urlGetFile) {
        this.urlGetFile = urlGetFile;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
