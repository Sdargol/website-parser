package com.sdargol.entities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "statistics")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "count_word")
    private Integer countWord;

    @Column(name = "stats")
    private byte[] stats;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    public Statistics() {
    }

    public Statistics(Integer id, Integer countWord, byte[] stats, Date date) {
        this.id = id;
        this.countWord = countWord;
        this.stats = stats;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountWord() {
        return countWord;
    }

    public void setCountWord(Integer countWord) {
        this.countWord = countWord;
    }

    public byte[] getStats() {
        return stats;
    }

    public void setStats(byte[] stats) {
        this.stats = stats;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", countWord=" + countWord +
                ", stats=" + Arrays.toString(stats) +
                ", date=" + date +
                '}';
    }
}
