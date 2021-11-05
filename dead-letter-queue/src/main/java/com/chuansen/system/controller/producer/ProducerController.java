package com.chuansen.system.controller.producer;

import com.chuansen.system.config.DeadLetterMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendOrder")
    public String sendOrder() {
        String msg = "producer正在做据传输测试";
        rabbitTemplate.convertAndSend(DeadLetterMQConfig.ORDER_EXCHANGE, DeadLetterMQConfig.ORDER_ROUTING_KEY, msg, message -> {
            // 设置消息过期时间 10秒过期
            message.getMessageProperties().setExpiration("10000");
            return message;
        });
        return "success";
    }
}
