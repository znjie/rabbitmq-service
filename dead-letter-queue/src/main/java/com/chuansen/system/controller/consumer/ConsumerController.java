package com.chuansen.system.controller.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerController {
    /**
     * 监听队列回调的方法
     * @param msg
     */
/*    @RabbitListener(queues = "zcs_order_queue")
    public void orderConsumer(String msg) {
        System.out.println("consumer正常订单消费者得到的消息结果："+msg);
    }*/
}