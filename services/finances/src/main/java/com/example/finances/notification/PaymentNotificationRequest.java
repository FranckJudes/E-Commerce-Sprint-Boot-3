package com.example.finances.notification;

import java.math.BigDecimal;

import com.example.finances.payments.PaymentMethod;

public record PaymentNotificationRequest(
    String orderReference,
    BigDecimal  amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String customerName,
    String customerEmail
)  {

}
