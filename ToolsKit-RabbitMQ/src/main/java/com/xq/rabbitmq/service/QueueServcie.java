package com.xq.rabbitmq.service;

import com.alibaba.fastjson.JSON;
import com.tools.comm.RedisService;
import com.tools.rabbitmq.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * @author admin3
 */
@Component
@Slf4j
@RabbitListener(queues = "QUEUE_HOLLE")
public class QueueServcie {

    @Autowired
    RedisService redisService;

    @RabbitHandler
    @ConditionalOnClass(User.class)
    public void getMessage(User msg){
        User user = JSON.parseObject(redisService.get("user").toString(), User.class);
        log.info("接收成功" + redisService.get("test") + user.toString()  + redisService.get("user"));
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + msg.toString());
    }
}
