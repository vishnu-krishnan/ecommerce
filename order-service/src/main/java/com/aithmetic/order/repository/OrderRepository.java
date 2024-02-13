package com.aithmetic.order.repository;

import com.aithmetic.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
        boolean existsByCustomerIdAndProductId(Long customerId, String productId);

    List<Order> findByCustomerId(Long customerId);
}
