package com.biz.constant.enums;

public enum RabbitMQEnum {

    QUEUE_1("/exchange1", "routing.key1", "queue1");

    private final String exchange;
    private final String routingKey;
    private final String queueName;

    RabbitMQEnum(String exchange, String routingKey, String queueName) {
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.queueName = queueName;
    }

    public String getExchange() { return exchange; }
    public String getRoutingKey() { return routingKey; }
    public String getQueueName() { return queueName; }
}