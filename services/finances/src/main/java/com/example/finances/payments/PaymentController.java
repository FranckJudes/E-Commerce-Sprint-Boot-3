package com.example.finances.payments;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    
    private final PaymentService paymentService;


    @PostMapping()
    public ResponseEntity<Integer> CreatePayment(@RequestBody @Valid PaymentRequest paymentRequest) {
        
        return ResponseEntity.ok(paymentService.createPayment(paymentRequest));
    }
    
}
