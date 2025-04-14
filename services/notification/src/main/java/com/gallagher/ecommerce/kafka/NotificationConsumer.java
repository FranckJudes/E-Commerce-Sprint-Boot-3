package com.gallagher.ecommerce.kafka;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.gallagher.ecommerce.Notification.Notification;
import com.gallagher.ecommerce.Notification.NotificationRepository;
import com.gallagher.ecommerce.Notification.NotificationType;
import com.gallagher.ecommerce.email.EmailService;
import com.gallagher.ecommerce.kafka.order.OrderConfirmation;
import com.gallagher.ecommerce.kafka.payement.PaymentConfirmation;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void  consumerPaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Payment confirmation received: {}", paymentConfirmation);
        // Save the notification to the database
        notificationRepository.save(
            Notification.builder()
                .paymentConfirmation(paymentConfirmation)
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .build()
        );
        

        var customerName = paymentConfirmation.customerFirstname()+" "+ paymentConfirmation.customerLastname();
        emailService.sentPaymentSuccessEmail(paymentConfirmation.customerEmail(), customerName, paymentConfirmation.totalAmount(), paymentConfirmation.orderReference());

    }

    @KafkaListener(topics = "order-topic")
    public void  consumerOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Payment confirmation received: {}", orderConfirmation);
        // Save the notification to the database
        notificationRepository.save(
            Notification.builder()
                .orderConfirmation(orderConfirmation)
                .type(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .build()
        );
        
       
        var customerName = orderConfirmation.Customer().firstname()+" "+ orderConfirmation.Customer().lastname();
        emailService.sentOrderConfirmationEmail(orderConfirmation.Customer().email(), customerName, orderConfirmation.totalAmount(), orderConfirmation.orderReference(), orderConfirmation.products());


    }
}
