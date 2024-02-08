package com.aithmetic.order.service;

import com.aithmetic.order.dto.OrderRequest;
import com.aithmetic.order.dto.OrderResponse;
import com.aithmetic.order.exception.ProductOutOfStockException;
import com.aithmetic.order.model.Order;
import com.aithmetic.order.repository.OrderRepository;
import com.aithmetic.product.service.ProductInventory;
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

    @Autowired
    private final ValidateOrderExist validateOrderExist;

    @Autowired
    private final ValidateOrderRequestBody validateOrderRequestBody;

    @Autowired
    private final ProductInventory productInventory;

    public void createOrder(OrderRequest orderRequest){

        if(!productInventory.checkProductQuantity(orderRequest.getProductId(), orderRequest.getQuantity())){
            log.info("Product is out of stock for product {} and requested quantity {}" ,orderRequest.getProductId(),orderRequest.getQuantity());
            throw new ProductOutOfStockException("Product is out of stock");

        }
        else {
            log.debug("Create request : {}", orderRequest);
            validateOrderRequestBody.orderFieldsValidation(orderRequest);
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
            log.info("Order {} is saved", order);
        }
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

    public List<Order> getOrderHistoryByCustomerId(Long customerId) {
        // Implement logic to fetch order history for the given customerId
        return orderRepository.findByCustomerId(customerId);
    }
}
