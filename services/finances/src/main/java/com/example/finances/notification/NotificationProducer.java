package com.example.finances.notification;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    
    private static final String PAYMENT_NOTIFICATION_TOPIC = "payment-notification";
    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

    public void sendNotification(PaymentNotificationRequest notification) {
        log.info("Sending notification to topic: {}", notification);
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(notification)
                .setHeader(KafkaHeaders.TOPIC, PAYMENT_NOTIFICATION_TOPIC)
                .build();
        
        kafkaTemplate.send(message);
    }  
}