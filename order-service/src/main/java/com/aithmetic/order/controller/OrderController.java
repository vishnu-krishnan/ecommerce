package com.aithmetic.order.controller;

import com.aithmetic.order.dto.OrderRequest;
import com.aithmetic.order.dto.OrderResponse;
import com.aithmetic.order.model.Order;
import com.aithmetic.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;


    @PostMapping(value = "/create")
    public ResponseEntity<String> createProduct(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully");
    }

    @GetMapping(value = "/getall")
    public ResponseEntity<Object> getAllOrders(){
        try{
            List<OrderResponse> orderResponses = orderService.getAllOrders();
            return ResponseEntity.ok(orderResponses);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occured while retrieving orders"+e.getMessage());
        }
    }
}
