package com.sdargol.dto;

import com.sdargol.entities.Site;
import com.sdargol.entities.Statistics;

public class SiteEntityToDTO {
    public static SiteDTO convert(Site site){
        return SiteDTOBuilder.getInstance()
                .setId(site.getId())
                .setName(site.getName())
                .setUrlGetFile(site.getFileName())
                .setStatus(site.getStatus())
                .setStatistics(convertStatistics(site.getStatistics()))
                .build();
    }

    private static StatisticsDTO convertStatistics(Statistics statistics){
        return StatisticsDTOBuilder.getInstance()
                .setCountWord(statistics.getCountWord())
                .setStats(statistics.getStats())
                .setDate(statistics.getDate())
                .build();

    }
}

