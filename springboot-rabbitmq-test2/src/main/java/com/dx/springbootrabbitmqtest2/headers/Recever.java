package com.dx.springbootrabbitmqtest2.headers;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component("headersRecever")
public class Recever {

    @RabbitListener(queues = "headers_queue")
    public void process(Message message) {
//        MessageProperties messageProperties = message.getMessageProperties();
//        String contentType = messageProperties.getContentType();
//        try {
//            System.out.println("header消费：" + new String(message.getBody(), contentType));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        System.out.println("消费：" + new String(message.getBody()));
    }
}
