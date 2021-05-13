package com.sdargol.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class ParserTaskBuilder<T> {
    private Supplier<T> downloadTask;
    private Consumer<T> parseTask;

    public ParserTaskBuilder() {
    }

    public ParserTaskBuilder<T> setDownloadTask(Supplier<T> downloadTask) {
        this.downloadTask = downloadTask;
        return this;
    }

    public ParserTaskBuilder<T> setParseTask(Consumer<T> parseTask) {
        this.parseTask = parseTask;
        return this;
    }

    public CompletableFuture<Void> build(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        return CompletableFuture.supplyAsync(downloadTask, threadPoolTaskExecutor)
                .thenAccept(parseTask);
    }
}
