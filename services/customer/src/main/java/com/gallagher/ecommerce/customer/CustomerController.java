package com.gallagher.ecommerce.customer;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor

public class CustomerController {

    private final CustomerService service;

    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
            return ResponseEntity.ok(service.createCustomer(request));
    }
}
