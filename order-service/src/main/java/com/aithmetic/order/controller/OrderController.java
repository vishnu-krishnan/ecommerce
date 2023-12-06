package com.aithmetic.order.controller;

import com.aithmetic.order.dto.OrderRequest;
import com.aithmetic.order.dto.OrderResponse;
import com.aithmetic.order.model.Order;
import com.aithmetic.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    @Autowired
    private final OrderService orderService;


    @PostMapping(value = "/create")
    public ResponseEntity<String> createProduct(@RequestBody OrderRequest orderRequest){
        try {
            orderService.createOrder(orderRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order placed successfully");
        }catch (Exception e){
            log.error("Exception occurred while creating Order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order: " + e.getMessage());
        }
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<Object> getAllOrders(){
        try{
            List<OrderResponse> orderResponses = orderService.getAllOrders();
            if (orderResponses.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data available");
            }
            return ResponseEntity.ok(orderResponses);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occured while retrieving order" +
                    "s"+e.getMessage());
        }
    }
}
