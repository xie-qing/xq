package com.xq.rabbitmq.service;

import com.xq.rabbitmq.mqenum.ExchangeEnum;
import com.xq.rabbitmq.mqenum.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author admin3
 */
public interface HollerService {

    /**
     * 发送消息到rabbitmq消息队列
     * @param message 消息内容
     * @param exchangeEnum 交换配置枚举
     * @param queueEnum 队列配置枚举
     * @throws Exception
     */
    public void send(Object message, ExchangeEnum exchangeEnum, QueueEnum queueEnum) throws Exception;

}
