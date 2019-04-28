package com.dx.springbootasync;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

@Component
public class Task {

    public static Random random = new Random();

    @Async
    public Future<String> T1() throws InterruptedException {
        System.out.println("执行任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务一耗时：" + (end - start) + "ms");
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public Future<String> T2() throws InterruptedException {
        System.out.println("执行任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务二耗时：" + (end - start) + "ms");
        return new AsyncResult<>("任务二完成");
    }

    @Async
    public Future<String> T3() throws InterruptedException {
        System.out.println("执行任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务三耗时：" + (end - start) + "ms");
        return new AsyncResult<>("任务三完成");
    }
}
