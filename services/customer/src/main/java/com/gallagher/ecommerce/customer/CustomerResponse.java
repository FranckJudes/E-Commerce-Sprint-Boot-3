package com.gallagher.ecommerce.customer;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

public record CustomerResponse(
        String id,
        @NotNull
        String firstname,

        @NotNull
        String lastname,

        @NotNull
        @Email
        String email,
        Address address
) {
}
