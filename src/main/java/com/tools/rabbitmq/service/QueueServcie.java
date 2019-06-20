package com.tools.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author admin3
 */
@Component
@RabbitListener(queues = "QUEUE_HOLLE")
public class QueueServcie {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitHandler
    public String getMessage(String msg){
        return msg;
    }
}
