package com.gallagher.ecommerce.orders;

import com.gallagher.ecommerce.orderline.OrderLineRequest;
import com.gallagher.ecommerce.orderline.OrderLineResponse;

import org.springframework.stereotype.Service;


@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        new OrderLine();
        return
                 OrderLine.builder()
                        .id(request.id())
                        .quantity((int) request.quantity())
                        .order(
                                Order.builder()
                                        .id(request.orderId())
                                        .build()
                        )
                        .produitId(request.productId())
                        .build();
    }


    public OrderLineResponse toOrderLineResponse(OrderLine orderLine){
            return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}

