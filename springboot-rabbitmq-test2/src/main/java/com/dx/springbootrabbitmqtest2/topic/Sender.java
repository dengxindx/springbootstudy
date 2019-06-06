package com.dx.springbootrabbitmqtest2.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("topicSender1")
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender1 : " + context);
        amqpTemplate.convertAndSend("mybootexchange", "topic.message", context);
    }


    public void send2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender2 : " + context);
        amqpTemplate.convertAndSend("mybootexchange", "topic.messages", context);
    }
}
