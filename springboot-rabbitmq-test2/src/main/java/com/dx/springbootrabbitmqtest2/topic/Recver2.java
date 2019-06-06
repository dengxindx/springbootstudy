package com.dx.springbootrabbitmqtest2.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者2号绑定队列q_topic_messages
 */
@Component("topicRecver2")
@RabbitListener(queues = "q_topic_messages")
public class Recver2 {

    @RabbitHandler
    public void process(String message) {
        System.out.println("消费者2号：" + message);
    }
}
