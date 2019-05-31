package com.dx.springbootrabbitmqmytest;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 消费者
 */
//@RabbitListener(queues = "t1")  // 监听t1队列
public class Receiver {

//    @RabbitHandler
//    public void process(String content) {
//        System.out.println("消费消息：" + content);
//    }
//
//    @RabbitHandler
//    public void processMessage(@Payload String body, @Headers Map<String, Object> headers) {
//        System.out.println("body："+body);
//        System.out.println("Headers："+headers);
//    }

    @RabbitListener(queues = "t1")
    public void processMessage1(Message bytes) {
        try {
            System.out.println("消费消息：" + new String(bytes.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("消费异常");
        }
    }

    @RabbitListener(queues = "t1")
    public void processMessage1(@Payload String body, @Headers Map<String,Object> headers) {
        System.out.println("body："+body);
        System.out.println("Headers："+headers);
    }
}
