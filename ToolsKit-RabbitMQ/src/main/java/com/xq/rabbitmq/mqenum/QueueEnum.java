package com.xq.rabbitmq.mqenum;

/**
 * @author admin3
 */

public enum QueueEnum {

    QUEUE_HOLLE("QUEUE_HOLLE", "QUEUE_HOLLE.routingKey"),
    USER_REGISTER("user.register.topic.queue" , "user.register.routingKey");

    String queue;
    String routingKey;

    private QueueEnum() {
    }

    private QueueEnum(String queue) {
        this.queue = queue;
    }

    private QueueEnum(String queue, String routingKey) {
        this.queue = queue;
        this.routingKey = routingKey;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
}
