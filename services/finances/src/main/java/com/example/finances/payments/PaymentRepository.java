package com.example.finances.payments;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // JpaRepository provides basic CRUD operations
    // Additional custom query methods can be defined here if needed
    Optional<Payment> findByOrderId(Integer orderId);

}
