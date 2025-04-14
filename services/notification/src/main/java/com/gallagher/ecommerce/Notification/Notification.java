package com.gallagher.ecommerce.Notification;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gallagher.ecommerce.kafka.order.OrderConfirmation;
import com.gallagher.ecommerce.kafka.payement.PaymentConfirmation;

import lombok.*;


/**
 * This class represents a notification in the e-commerce system.
 * It can be used to send alerts or messages to users.
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    
    @Id
    private String id;

    private NotificationType type;

    private LocalDateTime notificationDate;

    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
