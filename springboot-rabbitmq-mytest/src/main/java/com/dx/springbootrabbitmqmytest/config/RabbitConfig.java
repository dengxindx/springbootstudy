package com.dx.springbootrabbitmqmytest.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，用来配置队列、交换器、路由等高级信息
 *
 * 交换机四种调度策略：
 *
 */
@Configuration
public class RabbitConfig {

    /**
     * 创建一个队列t1
     * @return
     */
    @Bean
    public Queue helloQueue() {
        return new Queue("t1");
    }
}
