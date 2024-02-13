package com.aithmetic.order.service;

import com.aithmetic.order.dto.OrderRequest;
import com.aithmetic.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateOrderExist {

    @Autowired
    private final OrderRepository orderRepository;

    public void validateOrderExists(OrderRequest orderRequest) {
        if (orderRepository.existsByCustomerIdAndProductId(orderRequest.getCustomerId(), orderRequest.getProductId())) {
            throw new IllegalArgumentException("Customer has already ordered the product.");
        }
    }
}
