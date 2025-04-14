package com.example.finances.payments;

import org.springframework.stereotype.Service;

import com.example.finances.notification.NotificationProducer;
import com.example.finances.notification.PaymentNotificationRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {
        var payment = repository.save(mapper.toPayment(paymentRequest));

        var notification = new PaymentNotificationRequest(
            paymentRequest.orderReference(),
            payment.getAmount(),
            payment.getPaymentMethod(),
            payment.getOrderId(),
            paymentRequest.customer().firstname(),
            paymentRequest.customer().email()
        );

        notificationProducer.sendNotification(notification);
        
        return payment.getId();
    }
}