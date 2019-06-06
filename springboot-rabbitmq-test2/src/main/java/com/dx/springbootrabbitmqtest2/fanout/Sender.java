package com.dx.springbootrabbitmqtest2.fanout;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("fanoutSender")
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String context = "hi, fanout msg";
        System.out.println("生产消息：" + context);
        amqpTemplate.convertAndSend("mybootfanoutExchange", "", context);
    }
}
