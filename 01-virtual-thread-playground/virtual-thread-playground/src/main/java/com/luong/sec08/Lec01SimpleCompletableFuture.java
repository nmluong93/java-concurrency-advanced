package com.luong.sec08;

import com.luong.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*
    A very simple demo to show how CompletableFuture works
    Intentionally NOT using factory methods
 */
public class Lec01SimpleCompletableFuture {

    private static final Logger log = LoggerFactory.getLogger(Lec01SimpleCompletableFuture.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("main starts");
        var cf = slowTask();
        cf.thenAccept(v -> log.info("value={}", v));

//        log.info("Value = {}", cf.get());

//        log.info("value={}", cf.join());
        log.info("main ends");

        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<String> fastTask(){
        log.info("method starts");
        var cf = new CompletableFuture<String>();
        cf.complete("hi");
        log.info("method ends");
        return cf;
    }

    private static CompletableFuture<String> slowTask(){
        log.info("method starts");
        var cf = new CompletableFuture<String>();
        Thread.ofVirtual().start(() -> {
            log.info("Enter expensive task");
            CommonUtils.sleep(Duration.ofSeconds(1));
            log.info("Finish expensive task");
            cf.complete("hi");
        });
        log.info("method ends");
        return cf;
    }

}
