package com.sdargol.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ParseSiteRequest {
    @NotBlank
    @Size(min = 9)
    private String url;

    public ParseSiteRequest() {
    }

    public ParseSiteRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
