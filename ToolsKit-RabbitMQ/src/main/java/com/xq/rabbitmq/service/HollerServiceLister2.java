package com.xq.rabbitmq.service;

import com.xq.rabbitmq.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * $〉
 * 功能描述: <br>
 * 〈/$〉
 *
 * @author XQ
 * @date 2019/9/10 16:57
 */
@Component
@Slf4j
@RabbitListener(queues = "user.register.topic.queue")
public class HollerServiceLister2 {

    @RabbitHandler
    public void getMessage(User msg){
        log.info("消费者2号  " + msg.toString());
    }
}
