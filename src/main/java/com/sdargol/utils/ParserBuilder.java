package com.sdargol.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public final class ParserBuilder {
    private final Supplier<String> parser;
    private final Logger logger = LoggerFactory.getLogger(ParserBuilder.class);

    private ParserBuilder(Supplier<String> parser) {
        this.parser = parser;
    }

    public static ParserBuilder of(StringBuilder stringBuilder){
        return new ParserBuilder(stringBuilder::toString);
    }

    public ParserBuilder removeScript(){
        logger.info("Заменяем <script>...</script> на пробел");
        return new ParserBuilder(() -> parser.get().replaceAll("<script\\b[^<]*(?:(?!</script>)<[^<]*)*</script>", "\s"));
    }

    public ParserBuilder removeStyle(){
        logger.info("Заменяем <style>...</style> на пробел");
        return new ParserBuilder(() -> parser.get().replaceAll("<style\\b[^<]*(?:(?!</style>)<[^<]*)*</style>", "\s"));
    }

    public ParserBuilder removeHtmlTag(){
        logger.info("Удаляем HTML теги");
        return new ParserBuilder(() -> parser.get().replaceAll("<.*?>", "\s"));
    }

    public ParserBuilder trimTab(){
        logger.info("Заменяем табуляцию на пробел");
        return new ParserBuilder(() -> parser.get().replaceAll("\\t+", "\s"));
    }

    public ParserBuilder trimSpace(){
        logger.info("Заменяем последовательность пробелов на один пробел");
        return new ParserBuilder(() -> parser.get().replaceAll("\\s{2,}", "\s"));
    }

    public String[] toArray(){
        logger.info("Преобразуем в массив");
        return parser.get().split("[\\s?!\\-—.,\"“@;()\\[\\]{}:#&~`'«»/|]");
    }

    public List<String> toList(){
        logger.info("Преобразуем в List<String>");
        return Arrays.asList(toArray());
    }

    public String execute(){
        return parser.get();
    }
}

