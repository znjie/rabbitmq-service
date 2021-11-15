package com.chuansen.system.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConfig {
    /**
     * 定义交换机
     */
    public final static String EXCHANGE_SPRINGBOOT_NAME = "springboot_rabbit_exchange";
    /**
     * 订单队列
     */
    public final static String ORDER_QUEUE = "springboot_rabbit_order_queue";
    /**
     * 库存队列
     */
    public final static String STOCK_QUEUE = "springboot_rabbit_stock_queue";

    //配置订单队列
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE);
    }

    //配置库存队列
    @Bean
    public Queue stockQueue() {
        return new Queue(STOCK_QUEUE);
    }

    // 配置交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_SPRINGBOOT_NAME);
    }

    // 订单队列绑定交换机
    @Bean
    public Binding bindingOrderFanoutExchange(Queue orderQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(orderQueue).to(fanoutExchange);
    }

    // 存队列绑定交换机
    @Bean
    public Binding bindingStockFanoutExchange(Queue stockQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(stockQueue).to(fanoutExchange);
    }
}
