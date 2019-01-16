package com.microservice.skeleton.rabbitmq.service.rabbitListener;
import com.microservice.skeleton.rabbitmq.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void consumeMessage(String message) {
        System.out.println("rabbitConsumer----" + message);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void consumeMessage2(String message) {
        System.out.println("rabbitConsumer22----" + message);
    }
}

