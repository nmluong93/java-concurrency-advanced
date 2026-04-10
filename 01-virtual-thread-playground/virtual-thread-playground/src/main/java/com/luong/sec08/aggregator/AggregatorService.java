package com.luong.sec08.aggregator;

import com.luong.sec08.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class AggregatorService {

    private static final Logger log = LoggerFactory.getLogger(AggregatorService.class);
    private final ExecutorService executorService;

    public AggregatorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public ProductDto getProductDto(int id) {
        var product = CompletableFuture.supplyAsync(() -> Client.getProduct(id), executorService)
                                        // handle timeout
                                       .orTimeout(1250, TimeUnit.MILLISECONDS)
                                       .exceptionally(ex -> {
                                           log.error("get product error", ex);
                                           return null;
                                       });
        var rating = CompletableFuture.supplyAsync(() -> Client.getRating(id), executorService)
                                      .exceptionally(ex -> {
                                          log.warn("get rating failed {}", ex.getMessage());
                                          return -1;
                                      })
                                      // handle timeout
                                      .orTimeout(1250, TimeUnit.MILLISECONDS)
                                      .exceptionally(ex -> {
                                          log.warn("get rating timeout {}", ex.getMessage());
                                          return -2;
                                      });
        return new ProductDto(id, product.join(), rating.join());
    }

}
