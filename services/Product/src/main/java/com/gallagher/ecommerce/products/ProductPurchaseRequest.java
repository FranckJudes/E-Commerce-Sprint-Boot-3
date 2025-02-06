package com.gallagher.ecommerce.products;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer produitId,

        @NotNull(message = "Quabtity is mandatory")
        double quantity


) {
}
