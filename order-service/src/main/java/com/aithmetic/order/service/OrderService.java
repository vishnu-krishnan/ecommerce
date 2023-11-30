package com.aithmetic.order.service;

import com.aithmetic.order.dto.OrderRequest;
import com.aithmetic.order.dto.OrderResponse;
import com.aithmetic.order.model.Order;
import com.aithmetic.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public void createOrder(OrderRequest orderRequest){

        Order order = Order.builder()
                .customerId(orderRequest.getCustomerId())
                .productId(orderRequest.getProductId())
                .productName(orderRequest.getProductName())
                .quantity(orderRequest.getQuantity())
                .unitPrice(orderRequest.getUnitPrice())
                .totalPrice(orderRequest.getTotalPrice())
                .orderDate(orderRequest.getOrderDate())
                .shippingAddress(orderRequest.getShippingAddress())
                .shippingMethod(orderRequest.getShippingMethod())
                .orderStatus(orderRequest.getOrderStatus())
                .build();
        orderRepository.save(order);
        log.info("Product {} is saved",order);
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        log.error("No orders");
        return orders.stream().map(this::mapToOrderResponse).toList();
    }

    private OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .customerId(order.getCustomerId())
                .productId(order.getProductId())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .unitPrice(order.getUnitPrice())
                .totalPrice(order.getTotalPrice())
                .orderDate(order.getOrderDate())
                .shippingAddress(order.getShippingAddress())
                .shippingMethod(order.getShippingMethod())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}
