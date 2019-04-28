package com.dx.springbootasyncthreadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class Task {

    public static Random random = new Random();

    //使用自定义的线程池
    @Async("taskExecutor")
    public void t1() throws InterruptedException {
        log.info("执行任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        log.info("任务一耗时：" + (end - start) + "ms");
    }

    //使用自定义的线程池
    @Async("taskExecutor")
    public void t2() throws InterruptedException {
        log.info("执行任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        log.info("任务二耗时：" + (end - start) + "ms");
    }

    //使用自定义的线程池
    @Async("taskExecutor")
    public void t3() throws InterruptedException {
        log.info("执行任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        log.info("任务三耗时：" + (end - start) + "ms");
    }
}
