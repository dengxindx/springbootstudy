package com.dx.springbootasync;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Task3 {
    /**
     * 获取上一个阶段获取的值，返回转化后的值
     */
    @Test
    public void t1() {
        String result = CompletableFuture.supplyAsync(() -> "Hello").thenApplyAsync(v -> v + " world").join();
        System.out.println(result);
    }

    /**
     * 结合两个CompletionStage的结果，进行转化后返回
     */
    @Test
    public void t2() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "World";
        }), (s1, s2) -> s1 + " " + s2).join();

        System.out.println(result);
    }

    /**
     * 两个CompletionStage，谁计算的快，就用那个CompletionStage的结果进行下一步的处理
     */
    @Test
    public void t3() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Boy";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Girl";
        }), (s) -> s).join();
        System.out.println(result);
    }

    /**
     * 运行时出现了异常，可以通过exceptionally进行补偿
     */
    @Test
    public void t4() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("exception test!");
            }

            return "Hi Boy";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "Hello World";
        }).join();
        System.out.println(result);
    }
}
