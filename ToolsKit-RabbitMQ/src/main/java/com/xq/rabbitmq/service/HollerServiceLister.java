package com.xq.rabbitmq.service;

import com.alibaba.fastjson.JSON;
import com.xq.comm.RedisService;
import com.xq.rabbitmq.model.User;
import com.xq.rabbitmq.mqenum.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HollerServiceLister {

    @RabbitHandler
    public void getMessage(User msg){
        log.info("消费者1号 " + msg.toString());
    }
}
