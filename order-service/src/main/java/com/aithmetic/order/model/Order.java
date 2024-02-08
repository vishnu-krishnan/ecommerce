package com.aithmetic.order.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;
    private String productId;
    private String productName;

    /*@ManyToOne // Many orders belong to one customer //removed circular dependency
    @JoinColumn(name = "customer_id") // Specify the foreign key column
    private Customer customer;*/

    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;
    private String shippingAddress;
    private String shippingMethod;
    private String orderStatus;
}
