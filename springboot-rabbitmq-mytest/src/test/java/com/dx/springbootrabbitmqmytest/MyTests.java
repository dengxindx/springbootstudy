package com.dx.springbootrabbitmqmytest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class MyTests {

    @Test
    public void t1() {
//        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
//        map.put("a", null);
//        System.out.println(map);

//        Map hashMap = new HashMap();
//        hashMap.put(null, null);
//        System.out.println(hashMap);

//        List<String> list = new ArrayList<>();
//        list.add("张三");
//        list.add("李四");
//        list.add("王五");
//        list.add("赵六");
//        list.get(0);
//        for (String s : list) {
//            if ("李四".equals(s)) {
//                list.remove(s);
//            }
//            System.out.println(s);
//        }

        AtomicInteger a = new AtomicInteger(3);
        a.getAndDecrement();
        System.out.println(a);
    }

    public static void main(String[] args) throws InterruptedException{
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        service.execute(() -> {
            System.out.println("普通任务");
        });
        service.execute(() -> {
            System.out.println("普通任务");
        });
        service.execute(() -> {
            System.out.println("长时任务");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.execute(() -> {
            System.out.println("普通任务");
        });

        service.shutdown();
        // n秒之后还没有结束所有线程
        while (!service.awaitTermination(2, TimeUnit.SECONDS)) {
            System.out.println("线程池正在使用....");
        }

        System.out.println("线程池已经关闭");
    }


}
