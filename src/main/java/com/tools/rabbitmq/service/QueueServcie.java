package com.tools.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author admin3
 */
@Component
public class QueueServcie {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "QUEUE_HOLLE")
    public String getMessage(){
        String message = (String) rabbitTemplate.receiveAndConvert("QUEUE_HOLLE");
        return message;
    }
}
