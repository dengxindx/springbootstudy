package com.dx.springrabbitmqtest.topic;

import com.dx.springrabbitmqtest.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * topic策略，键匹配，#匹配一个或者多个，*匹配一个
 */
public class Send {

    private static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        int i = 1;
        do {
            // 消息内容
            String message = "Hello World!" + i++;
//            channel.basicPublish(EXCHANGE_NAME, "consume.1", null, message.getBytes());
            channel.basicPublish(EXCHANGE_NAME, "consume2.1", null, message.getBytes());
            System.out.println("生产消息:" + message + "'");
        }while (i <= 50);

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
