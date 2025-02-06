package com.gallagher.ecommerce.customer;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor

public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
            return ResponseEntity.ok(service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        service.UpdateCustomer(request);
        return ResponseEntity.accepted().build();
    }


    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(service.findAllCustomers());
    }


    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(service.existsById(customerId));
    }


    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(service.findById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<String> deleteById(@PathVariable("customer-id") String customerId) {
        service.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}
