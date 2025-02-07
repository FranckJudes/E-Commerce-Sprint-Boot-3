package com.gallagher.ecommerce.products;


import com.gallagher.ecommerce.exceptions.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(@Valid ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    /**
     * Ajoute les Produits dans le panier et retourne la liste
     * @param requests
     * @return List
     */
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        var products = requests.stream()
                .map(ProductPurchaseRequest::produitId)
                .toList();

        var storedProducts = repository.findAllByIdInOrderById(products);
        if (products.size() != storedProducts.size() ) {
            throw new ProductPurchaseException("One or more products does not exist");
        }

        var sortedRequest = requests.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::produitId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with id " + productRequest.produitId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity((int) newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toProductPurcharseResponse(product,productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Produit n'exites pas avec ID::" + productId));

    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                        .stream()
                        .map(mapper::toProductResponse)
                        .collect(Collectors.toList());
            }
}
