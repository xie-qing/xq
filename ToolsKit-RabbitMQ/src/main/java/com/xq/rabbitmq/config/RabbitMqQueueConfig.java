package com.xq.rabbitmq.config;

import com.xq.rabbitmq.mqenum.ExchangeEnum;
import com.xq.rabbitmq.mqenum.QueueEnum;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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
	 * 消息队列
	 */
	@Bean
	public Queue getUserQueue() {
		return new Queue(QueueEnum.USER_REGISTER.getQueue());
	}

	/**
	 * 用户交换器
	 */
	@Bean
	public DirectExchange getDirectExchange() {
		return new DirectExchange(ExchangeEnum.USER_REGISTER.getValue(),true ,false);
	}

	/**
	 * 绑定消息队列与交换器
	 */
	@Bean
	public Binding getBinding() {
		return BindingBuilder.bind(getUserQueue()).to(getDirectExchange()).with(QueueEnum.USER_REGISTER.getRoutingKey());
	}

}
