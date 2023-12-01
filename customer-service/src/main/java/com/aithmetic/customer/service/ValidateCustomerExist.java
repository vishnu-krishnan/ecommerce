package com.aithmetic.customer.service;

import com.aithmetic.customer.dto.CustomerRequest;
import com.aithmetic.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateCustomerExist {

    @Autowired
    private final CustomerRepository customerRepository;

    public void validateCustomerExists(CustomerRequest customerRequest) {
        if (customerRepository.existsByUsername(customerRequest.getUsername())) {
            throw new IllegalArgumentException("Username is already in use. Please choose a different username.");
        }
        if (customerRepository.existsByEmail(customerRequest.getEmail())) {
            throw new IllegalArgumentException("Email is already in use. Please choose a different email.");
        }
    }
}
