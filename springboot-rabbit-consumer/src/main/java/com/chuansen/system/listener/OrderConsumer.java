package com.chuansen.system.listener;

import com.chuansen.system.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class OrderConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "springboot_rabbit_order_queue")
    public void OrderData(@Payload byte[] message) {
        try {
            Order entity = objectMapper.readValue(message, Order.class);
            System.out.println("Order接收消息：" + entity.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}