package com.dx.springbootrabbitmqmytest;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 生产者
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     *  发送到hello队列中
     */
    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender:" + context);
        this.amqpTemplate.convertAndSend("t1", context);
    }


}
