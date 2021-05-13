package com.sdargol.entities;

import com.sdargol.utils.TextUtil;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SiteFactory implements IEntitiesFactory<Site>{
    static class StatisticsBuilder{
        private Integer id;
        private Integer countWord;
        private byte[] stats;
        private Date date;

        public StatisticsBuilder() {
        }

        public StatisticsBuilder setId(Integer id){
            this.id = id;
            return this;
        }

        public StatisticsBuilder setCountWord(Integer countWord){
            this.countWord = countWord;
            return this;
        }

        public StatisticsBuilder setStats(byte[] stats){
            this.stats = stats;
            return this;
        }

        public StatisticsBuilder setDate(Date date){
            this.date = date;
            return this;
        }

        public Statistics build(){
            return new Statistics(id,countWord,stats,date);
        }
    }

    static class SiteBuilder{
        private Integer id;
        private String name;
        private String fileName;
        private Status status;
        private Statistics statistics;

        public SiteBuilder() {
        }

        public SiteBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public SiteBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public SiteBuilder setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public SiteBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public SiteBuilder setStatistics(Statistics statistics) {
            this.statistics = statistics;
            return this;
        }

        public Site build(){
            return new Site(id,name,fileName,status,statistics);
        }
    }

    //при использовании данной реализации, нам необходимо самим установить name и fileName
    //т.к в бд есть ограничение на уникальность значения
    //в целом, данный класс предназначен для удобной первичной инициализации сущности
    @Override
    public Site create() {
        byte[] defaultValueByteArray = TextUtil.stringToArrayByte("empty");

        Statistics statistics = new StatisticsBuilder()
                .setId(0)
                .setCountWord(0)
                .setStats(defaultValueByteArray)
                .setDate(new Date())
                .build();

        return new SiteBuilder()
                .setId(0)
                .setName("default")
                .setFileName("default.html")
                .setStatus(Status.PROCESSING)
                .setStatistics(statistics)
                .build();
    }
}
