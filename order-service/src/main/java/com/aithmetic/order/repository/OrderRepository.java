package com.aithmetic.order.repository;

import com.aithmetic.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
        boolean existsByCustomerIdAndProductId(String customerId, String productId);
}
