package com.sdargol.dto;

import java.util.Date;
import java.util.List;

public final class StatisticsDTO {
    private Integer countWord;
    private List<WordDataDTO> stats;
    private Date date;

    public StatisticsDTO() {
    }

    public StatisticsDTO(Integer countWord, List<WordDataDTO> stats, Date date) {
        this.countWord = countWord;
        this.stats = stats;
        this.date = date;
    }

    public Integer getCountWord() {
        return countWord;
    }

    public void setCountWord(Integer countWord) {
        this.countWord = countWord;
    }

    public List<WordDataDTO> getStats() {
        return stats;
    }

    public void setStats(List<WordDataDTO> stats) {
        this.stats = stats;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
