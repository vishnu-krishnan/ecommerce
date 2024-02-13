package com.aithmetic.customer.controller;

import com.aithmetic.customer.dto.CustomerRequest;
import com.aithmetic.customer.dto.CustomerResponse;
import com.aithmetic.customer.model.Customer;
import com.aithmetic.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @PostMapping
    @RequestMapping(value = "/create")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest customerRequest){
        try {
            customerService.createCustomer(customerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully");
        }catch (Exception e){
            log.error("Exception occurred while creating customer: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating customer: " + e.getMessage());
        }
    }

    @GetMapping
    @RequestMapping(value = "/getall")
    public ResponseEntity<Object> getAllCustomers(){
        try {
            List<CustomerResponse> customerResponses = customerService.getAllCustomers();
            if (customerResponses.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data available");
            }
            return ResponseEntity.ok(customerResponses);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occured while retrieving orde" +
                    "rs: "+e.getMessage());
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        return ResponseEntity.ok(customer);
    }
}
