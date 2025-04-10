package com.gallagher.ecommerce.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {


    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmation orderConfigConfirmation) {
        log.info("Sending order confirmation to Kafka: {}", orderConfigConfirmation);

        Message<OrderConfirmation> message = MessageBuilder
                    .withPayload(orderConfigConfirmation)
                    .setHeader(KafkaHeaders.TOPIC, "order-topic")
                    .build();

        kafkaTemplate.send(message);
               
    }
    
}
