package com.sdargol.dto;

import com.sdargol.entities.Status;

public final class SiteDTO extends SiteDTOBase {
    private StatisticsDTO statistics;

    public SiteDTO() {
        super();
    }

    public SiteDTO(Integer id, String name, String urlGetFile, Status status, StatisticsDTO statistics) {
        super(id,name,urlGetFile,status);
        this.statistics = statistics;
    }

    public StatisticsDTO getStatistics() {
        return statistics;
    }

    public void setStatistics(StatisticsDTO statistics) {
        this.statistics = statistics;
    }
}
