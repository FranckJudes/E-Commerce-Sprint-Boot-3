package com.gallagher.ecommerce.orders;

import com.gallagher.ecommerce.customers.CustomerClient;
import com.gallagher.ecommerce.exceptions.BusinessException;
import com.gallagher.ecommerce.orderline.OrderLineRequest;
import com.gallagher.ecommerce.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrdeRepository repository;
    private final OrdeLineService orderlineService;
    private final OrderMapper mapper;
    public Integer createdOrder(OrderRequest request) {


        //Check the customer -> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot creeate order:: No Customer exists with the provided ID"));

        // Purchase the products -> products-ms (RestTemplates)

        this.productClient.purchaseProducts(request.products());

        // persist order
        var order = this.repository.save(mapper.toOrder(request));

        // persist order lines

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderlineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.produitId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //  Start payment process

        // send the confirmation -> notification-ms( kafka)

        return null;
    }
}
