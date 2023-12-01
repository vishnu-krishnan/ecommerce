package com.aithmetic.order.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Document(value = "order")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
