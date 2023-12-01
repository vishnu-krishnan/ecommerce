package com.aithmetic.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String id;
    private String customerId;
    private String productId;
    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;
    private String shippingAddress;
    private String shippingMethod;
    private String orderStatus;
}
