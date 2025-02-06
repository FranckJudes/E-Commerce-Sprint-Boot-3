package com.gallagher.ecommerce.products;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,

        @NotNull(message = "Le nom du produit est requis")
        String name,
        @NotNull(message = "La description du produit est requis")
        String description,
        @Positive(message = "La quantite du produit doit etre positif")
        @NotNull(message = "La quantite du produit est requis")
        double availableQuantity,
        @Positive(message = "Le prix du produit doit etre positif")
        BigDecimal price,
        @NotNull(message = "La categorie est requis")
        Integer category_id
) {


}
