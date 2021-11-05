package com.chuansen.system.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DeadLetterMQConfig {
    public static final String ORDER_EXCHANGE = "zcs_order_exchange";  //订单交换机
    public static final String DLX_EXCHANGE = "zcs_dlx_exchange";   //死信交换机
    public static final String ORDER_QUEUE = "zcs_order_queue";   //订单队列
    public static final String DLX_QUEUE = "zcs_order_dlx_queue";  //死信队列
    public static final String ORDER_ROUTING_KEY = "zcs.order";  //订单路由key
    public static final String DLX_ROUTING_KEY = "dlx";   //死信路由key

    /**
     * 声明订单交换机
     */
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE);
    }

    /**
     * 声明死信交换机
     */
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(DLX_EXCHANGE);
    }

    /**
     * 声明订单队列
     */
    @Bean
    public Queue orderQueue() {
        // 订单队列绑定我们的死信交换机
        Map<String, Object> arguments = new HashMap<>(2);
        arguments.put("x-dead-letter-exchange", DLX_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", DLX_ROUTING_KEY);
        return new Queue(ORDER_QUEUE, true, false, false, arguments);
    }

    /**
     * 声明死信队列
     */
    @Bean
    public Queue dlxQueue() {
        return new Queue(DLX_QUEUE);
    }

    /**
     * 绑定订单队列到订单交换机
     */
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(ORDER_ROUTING_KEY);
    }

    /**
     * 绑定死信队列到死信交换机
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with(DLX_ROUTING_KEY);
    }

}