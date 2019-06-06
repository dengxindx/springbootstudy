package com.dx.springbootrabbitmqtest2.headers;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * headers策略，和fanout一样不需要路由键routingkey
 */
@Configuration
public class HeaderRabbitConfig {

    @Bean(name = "headers_queue")
    Queue headers_queue() {
        return new Queue("headers_queue");
    }
    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange("headers_exchange");
    }

    @Bean
    Binding bindingExchangeTopicQueue2(@Qualifier("headers_queue") Queue queue, HeadersExchange headersExchange) {
        Map<String, Object> map = new HashMap<>();
        map.put("One","A");
        map.put("Two","B");

        //whereAll表示全部匹配
        return BindingBuilder.bind(queue).to(headersExchange).whereAll(map).match();

        //whereAny表示部分匹配
//        return BindingBuilder.bind(queue).to(headersExchange).whereAny(map).match();
    }
}
