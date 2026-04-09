package com.luong.sec07;

import com.luong.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.Executors;

/*
    ExecutorService now extends the AutoCloseable
*/
public class Lec01AutoCloseable {

    private static final Logger log = LoggerFactory.getLogger(Lec01AutoCloseable.class);

    public static void main(String[] args) {
        withAutoCloseable();
    }

    // w/o autocloseable - we have to issue shutdown for short-lived applications
    private static void withoutAutoCloseable(){
        var executorService = Executors.newSingleThreadExecutor();
        executorService.submit(Lec01AutoCloseable::task);
        log.info("submitted");
        // without explicitly shutdown called, the ExecutorService still running..., in this case the program never ends
        // with shutdown called, the ExecutorService will wait for running tasks finishes then shutdown
        executorService.shutdown();
        // use shutdownNow() will force shutdowning the ExecutorService, all running tasks will be cancelled, so
        // there is no guarantee that the running tasks will finish or not
    }

    private static void withAutoCloseable(){
        try(var executorService = Executors.newSingleThreadExecutor()){
            executorService.submit(Lec01AutoCloseable::task);
            executorService.submit(Lec01AutoCloseable::task);
            executorService.submit(Lec01AutoCloseable::task);
            executorService.submit(Lec01AutoCloseable::task);
            log.info("submitted");
        }
    }

    private static void task(){
        CommonUtils.sleep(Duration.ofSeconds(2));
        log.info("task executed");
    }

}
