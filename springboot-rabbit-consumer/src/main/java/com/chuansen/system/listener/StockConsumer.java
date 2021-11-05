package com.chuansen.system.listener;

import com.chuansen.system.entity.Order;
import com.chuansen.system.entity.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StockConsumer {

    @Autowired
    private ObjectMapper objectMapper;


    @RabbitListener(queues = "springboot_rabbit_stock_queue")
    public void OrderData(@Payload byte[] message) {
        try {
            Order entity = objectMapper.readValue(message, Order.class);
            //Stock entity = objectMapper.readValue(message, Stock.class);
            System.out.println("Stock接收订单分发的消息：" + entity.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
