package com.chuansen.system.controller.consumer;
import com.chuansen.system.config.DeadLetterMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerDlxController {
    /**
     * 监听队列回调的方法
     * @param msg
     */
    @RabbitListener(queues = DeadLetterMQConfig.DLX_QUEUE)
    public void orderConsumer(String msg) {
        System.out.println("consumer死信订单消费者得到的消息结果："+msg);
    }
}
