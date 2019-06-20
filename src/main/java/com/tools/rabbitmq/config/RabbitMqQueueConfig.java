package com.tools.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tools.rabbitmq.mqenum.QueueEnum;


/**
 * @author admin3
 */
@Configuration
public class RabbitMqQueueConfig {

	/**
	 * 消息队列
	 */
	@Bean
	public Queue getQueue() {
		return new Queue(QueueEnum.QUEUE_HOLLE.getQueue());
	}

	/**
	 * 交换器
	 */
	@Bean
	public DirectExchange getDirectExchange() {
		return new DirectExchange("DirectExchange_Holle" ,true ,false);
	}

	/**
	 * 绑定消息队列与交换器
	 */
	@Bean
	public Binding getBinding() {
		return BindingBuilder.bind(getQueue()).to(getDirectExchange()).with("Binding_Holle");
	}

}
