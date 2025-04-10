package com.gallagher.ecommerce.orders;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gallagher.ecommerce.orderline.OrderLineResponse;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

    List <OrderLine> findAllByOrderId(Integer orderId);
}
