package com.luong.sec02;

import com.luong.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/*
    Some chained method calls which could throw exceptions
 */

public class Task {

    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void execute(int i){
        log.info("starting task {}", i);
        try{
            method1(i);
        }catch (Exception e){
            log.error("error for {}", i, e);
        }
        log.info("ending task {}", i);
    }

    private static void method1(int i){
        CommonUtils.sleep(Duration.ofMillis(300));
        try{
            method2(i);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void method2(int i){
        CommonUtils.sleep(Duration.ofMillis(100));
        method3(i);
    }

    private static void method3(int i){
        CommonUtils.sleep(Duration.ofMillis(500));
        if(i == 4){
            throw new IllegalArgumentException("i can not be 4");
        }
    }

    public static long findFib(long index) {
        if (index < 2) {
            return index;
        }
        return findFib(index - 1) + findFib(index - 2);
    }
}
