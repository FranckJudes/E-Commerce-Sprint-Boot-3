package com.example.finances.payments;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finances.notification.NotificationProducer;
import com.example.finances.notification.PaymentNotificationRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;
    
    @Transactional
    public Integer createPayment(PaymentRequest paymentRequest) {
        System.out.println(paymentRequest);
        repository.findByOrderId(paymentRequest.orderId())
            .ifPresent(p -> {
                throw new RuntimeException("Payment already exists for order: " + p.getOrderId());
            });
            
        var payment = mapper.toPayment(paymentRequest);
        payment = repository.save(payment);
        
        sendNotificationAsync(paymentRequest, payment);
        
        return payment.getId();
    }
    
    @Async
    public void sendNotificationAsync(PaymentRequest paymentRequest, Payment payment) {
        var notification = new PaymentNotificationRequest(
            paymentRequest.orderReference(),
            payment.getAmount(),
            payment.getPaymentMethod(),
            payment.getOrderId(),
            paymentRequest.customer().firstname(),
            paymentRequest.customer().email()
        );
        notificationProducer.sendNotification(notification);
    }
}