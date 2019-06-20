package com.tools.rabbitmq.controller;

import com.tools.rabbitmq.service.QueueServcie;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin3
 */
@RestController
public class HollerController {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    QueueServcie queueServcie;


    @GetMapping("/sendMsg")
    public String sendMessage(){
        String msgText = "dsafds";
//        rabbitTemplate.convertAndSend("Binding_Holle" , msgText);
        rabbitTemplate.convertAndSend(msgText);
        return  "发送成功！";
    }

    @GetMapping("getMessage")
    public String getMessage(){
        return "接收成功" + queueServcie.getMessage();
    }
	

}
