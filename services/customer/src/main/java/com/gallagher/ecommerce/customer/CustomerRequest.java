package com.gallagher.ecommerce.customer;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

public record CustomerRequest(
         String id,
         @NotNull(message = "Le firstname du client est requis")
         String firstname,

         @NotNull(message = "Le lastname du client est requis")
         String lastname,

         @NotNull(message = "Le lastname du client est requis")
         @Email(message = "Le email du client est requis")
         String email,
         Address address
) {


}
