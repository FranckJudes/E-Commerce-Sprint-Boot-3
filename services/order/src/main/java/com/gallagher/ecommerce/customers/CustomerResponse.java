package com.gallagher.ecommerce.customers;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
