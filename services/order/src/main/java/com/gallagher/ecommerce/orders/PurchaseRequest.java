package com.gallagher.ecommerce.orders;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product ist mandatory")
        Integer produitId,

        @Positive(message = "Quantiry is mandatory" )
        double quantity

) {
    }

