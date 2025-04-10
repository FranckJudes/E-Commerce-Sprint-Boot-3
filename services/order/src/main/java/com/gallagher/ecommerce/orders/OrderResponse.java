package com.gallagher.ecommerce.orders;

import java.math.BigDecimal;

public record OrderResponse(

    Integer id,
    String reference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerId
)  {

    
}
