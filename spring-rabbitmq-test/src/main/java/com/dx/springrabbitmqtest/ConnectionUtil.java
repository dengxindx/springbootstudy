package com.dx.springrabbitmqtest;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 获取MQ的链接
 */
public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        // 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置服务器地址
        factory.setHost("localhost");
        // 端口
//        factory.setPort(5672);
        factory.setPort(AMQP.PROTOCOL.PORT);
        // 设置账号信息，用户名，密码，vhost
        factory.setVirtualHost("testhost");
        factory.setUsername("dx");
        factory.setPassword("123");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
