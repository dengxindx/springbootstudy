package com.dx.springbootschedul;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 每隔5秒输出一次
     */
    @Scheduled(fixedRate = 1000 * 5)
    public void reportCurrentTime() {
        System.out.println("当前时间：" + sdf.format(new Date()));
    }

    /**
     * 延时5秒后输出
     */
    @Scheduled(fixedDelay = 1000 * 5)
    public void reportCurrentTime2() {
        System.out.println("test2：" + sdf.format(new Date()));
    }

    /**
     * 第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
     */
    @Scheduled(initialDelay = 1000, fixedRate = 1000 * 5)
    public void reportCurrentTime3() {
        System.out.println("test3：" + sdf.format(new Date()));
    }

    /**
     * 通过cron表达式定义规则
     */
    @Scheduled(cron = "*/5 * * * * *")
    public void reportCurrentTime4() {
        System.out.println("test4：" + sdf.format(new Date()));
    }
}
