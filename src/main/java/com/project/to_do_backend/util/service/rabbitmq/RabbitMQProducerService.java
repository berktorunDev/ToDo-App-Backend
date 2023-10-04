package com.project.to_do_backend.util.service.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import org.springframework.amqp.core.Queue;

@Service
public class RabbitMQProducerService {

    private final RabbitTemplate rabbitTemplate;
    private final Queue notificationQueue; // The queue where notifications will be sent

    public RabbitMQProducerService(RabbitTemplate rabbitTemplate, Queue notificationQueue) {
        // Constructor to inject RabbitMQ dependencies
        this.rabbitTemplate = rabbitTemplate;
        this.notificationQueue = notificationQueue;
    }

    public void sendMessage(String message) {
        // This method sends a message to the RabbitMQ notification queue.
        // It uses RabbitTemplate to convert and send the message to the specified
        // queue.
        rabbitTemplate.convertAndSend(notificationQueue.getName(), message);
    }
}
