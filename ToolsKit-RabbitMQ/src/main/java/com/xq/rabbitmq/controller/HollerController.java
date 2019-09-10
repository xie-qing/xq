package com.xq.rabbitmq.controller;

import com.xq.rabbitmq.model.User;
import com.xq.rabbitmq.mqenum.ExchangeEnum;
import com.xq.rabbitmq.mqenum.QueueEnum;
import com.xq.rabbitmq.service.HollerService;
import com.xq.comm.RedisService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author admin3
 */
@RestController
public class HollerController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RedisService redisService;

    @Resource
    HollerService hollerService;


    @GetMapping("/sendMsg")
    public String sendMessage() {
        String msgText = "dsafds";
        redisService.set("test", "你好");
        redisService.set("user", new User(18, "张三"));
        rabbitTemplate.convertAndSend("QUEUE_HOLLE", new User(18, "张三"));
        return "发送成功！";
    }


    @GetMapping("/sendUserMsg")
    public String sendUserMessage() throws Exception {
        hollerService.send(new User(24, "李四") , ExchangeEnum.USER_REGISTER , QueueEnum.USER_REGISTER);
        return "发送成功！";
    }


}
