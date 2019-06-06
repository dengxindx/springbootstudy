package com.dx.springbootrabbitmqtest2.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者1号绑定队列q_topic_message
 */
@Component("topicRecver1")
@RabbitListener(queues = "q_topic_message")
public class Recver {

    @RabbitHandler
    public void process(String message) {
        System.out.println("消费者1号：" + message);
    }
}
