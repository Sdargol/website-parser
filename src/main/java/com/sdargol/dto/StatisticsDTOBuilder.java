package com.sdargol.dto;

import com.sdargol.utils.ConverterUtil;

import java.util.Date;
import java.util.List;

public class StatisticsDTOBuilder {
    private Integer countWord;
    private List<WordDataDTO> stats;
    private Date date;

    private StatisticsDTOBuilder() {
    }

    public static StatisticsDTOBuilder getInstance(){
        return new StatisticsDTOBuilder();
    }


    public StatisticsDTOBuilder setCountWord(Integer countWord) {
        this.countWord = countWord;
        return this;
    }

    public StatisticsDTOBuilder setStats(byte[] stats) {
        this.stats = ConverterUtil.toEntity(stats);
        return this;
    }

    public StatisticsDTOBuilder setDate(Date date) {
        this.date = date;
        return this;
    }

    public StatisticsDTO build(){
        return new StatisticsDTO(countWord,stats,date);
    }
}
