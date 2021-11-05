package com.chuansen.system.controller.consumer;

import com.chuansen.system.config.RabbitMQConfig;
import com.chuansen.system.entity.Order;
import com.chuansen.system.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConsumerController {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderService orderService;

    /**
     * 如果客户端在消费消息期间报错，rabbitmq选择   重试机制，那么可以在配置文件中设置重试次数吗，时间间隔
     * 如果去处理重试失败这些问题？
     * 方式1.消费者接受消息报错，可以用try{}catch去处理   写进日志表里面，在根据日志记录+定时任务不定时的去检查处理消费失败重试的问题
     * 方式2.如果是重试多次还是失败消息，需要重新发布消费者版本实现消费，可以使用死信队列
     *
     *    Mq在重试的过程中，有可能会引发消费者重复消费的问题。
     *    Mq消费者需要解决 幂等性问题
     *    幂等性 保证数据唯一
     * 方式1：生产者在投递消息的时候，生成一个全局唯一id，放在我们消息中。
     *       实际上，全局唯一id 根据业务来定的，实际上还是需要再db层面解决数据防重复。业务逻辑是在做insert操作使用唯一主键约束
     *
     * @param message
     */
    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void OrderData(@Payload byte[] message) {
        try {
            Order entity = objectMapper.readValue(message, Order.class);
            System.out.println("Order接收消息：" + entity.toString());
            orderService.save(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
