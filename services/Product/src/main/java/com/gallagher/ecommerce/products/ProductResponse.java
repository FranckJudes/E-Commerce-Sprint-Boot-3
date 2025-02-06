package com.gallagher.ecommerce.products;

import jakarta.persistence.*;

import java.math.BigDecimal;

public record ProductResponse(
         Integer idm,
         String name,

         String description,

         Integer availableQuantity,

         BigDecimal price,

         Integer categoryId,

         String categoryName,
         String categoryDescription
) {

}
