package com.gallagher.ecommerce.orders;


import com.gallagher.ecommerce.orderline.OrderLineRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrdeLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order  = mapper.toOrderLine(orderLineRequest);
        return Integer.valueOf(repository.save(order).getId());
    }
}
