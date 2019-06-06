package com.dx.springrabbitmqtest.direct;

import com.dx.springrabbitmqtest.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


/**
 * direct策略，键名完全匹配
 */
public class Send {

    private static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明交换机，direct策略
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        int i = 1;
        do {
            // 消息内容
            String message = "Hello World!" + i;

            //交换机，key，，
            channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());
            System.out.println("生产消息：" + message);
            i++;
        }while (i <= 50);

        channel.close();
        connection.close();
    }
}
