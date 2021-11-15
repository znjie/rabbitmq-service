package com.chuansen.system.controller;

import com.chuansen.system.config.RabbitMQConfig;
import com.chuansen.system.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;
@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("/sendMag")
    public ResponseEntity sendMag(){
        ResponseEntity responseEntity=new ResponseEntity(HttpStatus.OK);
        Order order= new Order(UUID.randomUUID().toString(),new Date().toInstant(),null,(UUID.randomUUID().toString()+System.currentTimeMillis()),
                "测试商品",2,102.11,"广州市天河区体育中心金中环大厦A座35楼3501");
        try {
            System.out.println("发送订单实体对象"+order.toString());
            rabbitTemplate.setExchange(RabbitMQConfig.EXCHANGE_SPRINGBOOT_NAME);
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            Message msg = MessageBuilder.withBody(objectMapper.writeValueAsBytes(order)).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT).build();
            rabbitTemplate.convertAndSend(msg);
        } catch (Exception e) {
            System.out.println("发送消息异常:"+e.fillInStackTrace());
        }
        return responseEntity;
    }
}
