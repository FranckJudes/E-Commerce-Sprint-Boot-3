package com.gallagher.ecommerce.orders;

import com.gallagher.ecommerce.customers.CustomerClient;
import com.gallagher.ecommerce.exceptions.BusinessException;
import com.gallagher.ecommerce.kafka.OrderConfirmation;
import com.gallagher.ecommerce.kafka.OrderProducer;
import com.gallagher.ecommerce.orderline.OrderLineRequest;
import com.gallagher.ecommerce.product.ProductClient;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrdeRepository repository;
    private final OrdeLineService orderlineService;
    private final OrderMapper mapper;
    private final OrderProducer orderProducer;

    public Integer createdOrder(OrderRequest request) {


        //Check the customer -> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot creeate order:: No Customer exists with the provided ID"));

        // Purchase the products -> products-ms (RestTemplates)

        var purchaseProducts = this.productClient.purchaseProducts(request.products());

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

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(request.reference(), request.amount(), request.paymentMethod(), customer, purchaseProducts)
        );

        // send the confirmation -> notification-ms( kafka)

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find order with the provided ID"));
    }
}
