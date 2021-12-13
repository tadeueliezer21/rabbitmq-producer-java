package com.bc.producermessage.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserAMQPConfig {

    public static final String EXCHANGE = "amq.direct";
    public static final String QUEUE = "user-created";

    private AmqpAdmin amqpAdmin;

    public UserAMQPConfig(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private DirectExchange declareExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    private Queue declareQueue() {
        return new Queue(QUEUE, true, false, false);
    }

    private Binding declareBinding(Exchange exchange, Queue queue) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void init() {
        this.amqpAdmin.declareBinding(declareBinding(declareExchange(), declareQueue()));
    }

}
