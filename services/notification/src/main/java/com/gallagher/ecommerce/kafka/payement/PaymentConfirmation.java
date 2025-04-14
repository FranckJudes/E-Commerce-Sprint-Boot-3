package com.gallagher.ecommerce.kafka.payement;

import java.math.BigDecimal;

public record PaymentConfirmation(

        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail


) {
    
}
