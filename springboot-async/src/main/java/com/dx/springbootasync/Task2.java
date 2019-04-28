package com.dx.springbootasync;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Component
public class Task2 {

    public static Random random = new Random();

    @Async
    public CompletableFuture<String> T1() throws InterruptedException {
        System.out.println("执行任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务一耗时：" + (end - start) + "ms");
        return CompletableFuture.supplyAsync(() -> "任务一完成");
    }

    @Async
    public CompletableFuture<String> T2() throws InterruptedException {
        System.out.println("执行任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务二耗时：" + (end - start) + "ms");
        return CompletableFuture.supplyAsync(() -> "任务二完成");
    }

    @Async
    public CompletableFuture<String> T3() throws InterruptedException {
        System.out.println("执行任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务三耗时：" + (end - start) + "ms");
        return CompletableFuture.supplyAsync(() -> "任务三完成");
    }
}
