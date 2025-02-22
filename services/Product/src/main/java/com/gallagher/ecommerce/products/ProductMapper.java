package com.gallagher.ecommerce.products;


import com.gallagher.ecommerce.Categories.Category;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .availableQuantity((int) request.availableQuantity())
                .category(Category.builder()
                                                 .id(request.category_id())
                                                 .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurcharseResponse(Product product, @NotNull(message = "Quabtity is mandatory") double quantity) {
        return new ProductPurchaseResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            quantity
        );
    }
}
