package com.gallagher.ecommerce.payment;

import java.math.BigDecimal;

import com.gallagher.ecommerce.customers.CustomerResponse;
import com.gallagher.ecommerce.orders.PaymentMethod;


public record PaymentRequest(
        
        BigDecimal amount,
        
        PaymentMethod paymentMethod,

        Integer orderId,

        String orderReference,

        CustomerResponse customer
)  {

}
