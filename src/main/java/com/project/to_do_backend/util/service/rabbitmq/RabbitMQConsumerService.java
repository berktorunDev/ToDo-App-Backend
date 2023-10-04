package com.project.to_do_backend.util.service.rabbitmq;

import org.springframework.stereotype.Service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Service
public class RabbitMQConsumerService {

    @RabbitListener(queues = "notification-queue")
    public void receiveMessage(String message) {
        // This method listens to the "notification-queue" in RabbitMQ and processes
        // incoming messages.
        // It is annotated with @RabbitListener to specify the queue to listen to.

        // For testing and debugging purposes in the absence of a frontend interface,
        // we print the received message to the console.
        System.out.println("Received message as notification from RabbitMQ: " + message);

        // In a production environment with a frontend, this message could be further
        // processed
        // and displayed to users through the frontend interface.
        // However, in this scenario, we are printing it to the console for testing
        // purposes.
    }
}
