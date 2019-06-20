package com.tools.rabbitmq.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tools.comm.RedisService;
import com.tools.rabbitmq.User;
import com.tools.rabbitmq.mqenum.QueueEnum;
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
    RedisService redisService;

    @Autowired
    QueueServcie queueServcie;


    @GetMapping("/sendMsg")
    public String sendMessage() {
        String msgText = "dsafds";
//        rabbitTemplate.convertAndSend("Binding_Holle" , msgText);
        redisService.set("test", "你好");
        redisService.set("user", new User(18, "张三"));
        rabbitTemplate.convertAndSend("QUEUE_HOLLE", "Hello RabbitMQ!");
        return "发送成功！";
    }

    @GetMapping("getMessage")
    public String getMessage() {
        User user = JSON.parseObject(redisService.get("user").toString(), User.class);
        return "接收成功" + redisService.get("test") + user.toString() + queueServcie.getMessage() + redisService.get("user");
    }


}
