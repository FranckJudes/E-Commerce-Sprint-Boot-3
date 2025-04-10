package com.gallagher.ecommerce.kafka;

import java.math.BigDecimal;
import java.util.List;


import com.gallagher.ecommerce.customers.CustomerResponse;
import com.gallagher.ecommerce.orders.PaymentMethod;
import com.gallagher.ecommerce.product.PurchaseResponse;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    CustomerResponse CustomerResponse,
    List<PurchaseResponse> products
) {
    
}
