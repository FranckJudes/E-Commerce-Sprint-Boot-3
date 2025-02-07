package com.gallagher.ecommerce.orders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,
        String reference,

        @Positive(message = "Order amoubt should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be presend")
        @NotEmpty(message = "Customer should be presend")
        @NotBlank(message = "Customer should be presend")

        String customerId,

        @NotEmpty(message = "You should at least purchase un product")
        List<OrderRequest> products
) {
}
