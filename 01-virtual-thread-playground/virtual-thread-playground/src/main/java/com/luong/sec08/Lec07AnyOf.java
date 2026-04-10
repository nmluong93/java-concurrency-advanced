package com.luong.sec08;

import com.luong.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Lec07AnyOf {

    private static final Logger log = LoggerFactory.getLogger(Lec07AnyOf.class);

    public static void main(String[] args) {

            try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
                var cf1 = getDeltaAirfare(executor);
                var cf2 = getFrontierAirfare(executor);
                log.info("Result From ={}", CompletableFuture.anyOf(cf1, cf2).join());
            }

    }

    private static CompletableFuture<String> getDeltaAirfare(ExecutorService executor){
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            log.info("Start call of DeltaAirfare which will takes {} ms", random);
            CommonUtils.sleep(Duration.ofMillis(random));
            log.info("End call of DeltaAirfare");
            return "Delta-$" + random;
        }, executor);
    }

    private static CompletableFuture<String> getFrontierAirfare(ExecutorService executor){
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            log.info("Start call of FrontierAirfare which will takes {} ms", random);
            CommonUtils.sleep(Duration.ofMillis(random));
            log.info("End call of FrontierAirfare");
            return "Frontier-$" + random;
        }, executor);
    }

}
