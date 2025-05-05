package com.example.finances.payments;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        
        PaymentMethod paymentMethod,

        Integer orderId,

        String orderReference,

        Customer customer
     

)  {

    
}
