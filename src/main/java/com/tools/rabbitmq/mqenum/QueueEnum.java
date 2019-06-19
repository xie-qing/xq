package com.tools.rabbitmq.mqenum;

public enum QueueEnum {

	QUEUE_HOLLE("queue_holle","测试通道");

	String queue;
	String queueName;

	private QueueEnum() {
	}

	private QueueEnum(String queue) {
		this.queue = queue;
	}

	private QueueEnum(String queue, String queueName) {
		this.queue = queue;
		this.queueName = queueName;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

}
