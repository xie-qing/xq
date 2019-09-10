package com.xq.rabbitmq.service.Impl;

import com.xq.rabbitmq.mqenum.ExchangeEnum;
import com.xq.rabbitmq.mqenum.QueueEnum;
import com.xq.rabbitmq.service.HollerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * $〉
 * 功能描述: <br>
 * 〈/$〉
 *
 * @author XQ
 * @date 2019/9/10 16:32
 */
@Service
@Slf4j
public class HollerServiceImpl implements HollerService, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void send(Object message, ExchangeEnum exchangeEnum, QueueEnum queueEnum) throws Exception {
        //设置回调为当前类对象
//        rabbitTemplate.setConfirmCallback(this::confirm);
        //设置回调失败对象
//        rabbitTemplate.setReturnCallback(this::returnedMessage);
        //构建回调id为uuid
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //发送消息到消息队列
        rabbitTemplate.convertAndSend(exchangeEnum.getValue(), queueEnum.getRoutingKey(), message, correlationId);
    }



    /**
     * 〈〉
     * 功能描述: 消息消费成功回调接口<br>
     * 〈/〉
     *
     * @param
     * @return
     * @author XQ
     * @date 2019/9/10 16:35
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("回调id" + correlationData.getId() + " - " + correlationData.getReturnedMessage());
        if (ack) {
            log.info("消息消费成功！");
        } else {
            log.info("消息消费失败！" + cause);
        }
    }

    /**
     * 〈〉
     * 功能描述: 消息消费失败回调接口<br>
     * 〈/〉
     *
     * @param
     * @return
     * @author XQ
     * @date 2019/9/10 16:35
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey,
                replyCode, replyText, message);
    }
}
