package com.gallagher.ecommerce.orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdeRepository extends JpaRepository<Order, Integer> {
}
