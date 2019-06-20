package com.tools.rabbitmq.mqenum;

/**
 * @author admin3
 */

public enum QueueEnum {

    QUEUE_HOLLE("QUEUE_HOLLE", "测试通道");

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
