package com.dx.springbootrabbitmqtest2.single;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component("singleRecver1")
@RabbitListener(queues = "test2")
public class Recver {

    @RabbitHandler
    public void process(String message) {
        System.out.println("消费者消费1号：" + message);
    }

//    @RabbitHandler
//    public void process2(String message) {
//        System.out.println("消费者消费2号：" + message);
//    }
}
