package com.gallagher.ecommerce.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import com.gallagher.ecommerce.kafka.payement.PaymentMethod;

public record OrderConfirmation(

        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer Customer,
        List<Product> products
) {
    
}
