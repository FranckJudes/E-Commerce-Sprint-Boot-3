package com.gallagher.ecommerce.orderline;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gallagher.ecommerce.orders.OrderLineMapper;
import com.gallagher.ecommerce.orders.OrderLineRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;

    private final OrderLineMapper orderLineMapper;
    
  

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
       return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }

}
