package com.gallagher.ecommerce.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "PAYMENT-SERVICE",
        url = "http://localhost:8222/PAYMENT-SERVICE/api/v1/payments"
)
public interface PaymentClient {

    @PostMapping
    Integer requestOrderPayment(@RequestBody PaymentRequest paymentRequest);
}
