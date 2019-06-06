package com.dx.springbootrabbitmqtest2.headers;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("headersSender")
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        Map<String, Object> map_any = new HashMap<>();
        map_any.put("Three", "C");

        MessageProperties messageProperties = new MessageProperties();
        // 设置消息是否持久化。Persistent表示持久化，Non-persistent表示不持久化
        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
//        messageProperties.setContentType("UTF-8");
        messageProperties.getHeaders().putAll(map_any);

        String context = "headers策略测试1";
        System.out.println("生产消息：" + context);
        Message message = new Message(context.getBytes(), messageProperties);

        amqpTemplate.convertAndSend("headers_exchange", null, message);
    }

    public void send2() {
        Map<String, Object> map_all = new HashMap<>();
        map_all.put("One", "A");
        map_all.put("Two", "B");

        /**
         * 声明消息 (消息体, 消息属性)
         */
        MessageProperties messageProperties = new MessageProperties();
        // 设置消息是否持久化。Persistent表示持久化，Non-persistent表示不持久化
        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
//        messageProperties.setContentType("UTF-8");
        messageProperties.getHeaders().putAll(map_all);


        String context = "headers策略测试2";
        System.out.println("生产消息：" + context);

        Message message = new Message(context.getBytes(), messageProperties);

        amqpTemplate.convertAndSend("headers_exchange", null, message);
    }

    public void send3() {
        Map<String, Object> map_any = new HashMap<>();
        map_any.put("One", "A");

        /**
         * 声明消息 (消息体, 消息属性)
         */
        MessageProperties messageProperties = new MessageProperties();
        // 设置消息是否持久化。Persistent表示持久化，Non-persistent表示不持久化
        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
//        messageProperties.setContentType("UTF-8");
        messageProperties.getHeaders().putAll(map_any);


        String context = "headers策略测试3";
        System.out.println("生产消息：" + context);

        Message message = new Message(context.getBytes(), messageProperties);

        amqpTemplate.convertAndSend("headers_exchange", null, message);
    }
}
