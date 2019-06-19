package com.tools.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tools.rabbitmq.mqenum.QueueEnum;


@Configuration
public class RabbitMqQueueConfig {
	
	@Bean
	public Queue getQueue() {
		return new Queue(QueueEnum.QUEUE_HOLLE.getQueue());
	}

}
