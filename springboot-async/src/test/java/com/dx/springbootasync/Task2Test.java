package com.dx.springbootasync;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Task2Test {

    @Autowired
    private Task2 task2;

    @Test
    public void t1() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();

        CompletableFuture<String> t1 = task2.T1();
        CompletableFuture<String> t2 = task2.T2();
        CompletableFuture<String> t3 = task2.T3();

        List<CompletableFuture<String>> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);
        list.add(t3);

        for (CompletableFuture<String> future : list) {
            System.out.println(future.get());
        }

        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }
}