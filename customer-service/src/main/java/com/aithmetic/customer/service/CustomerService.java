package com.aithmetic.customer.service;

import com.aithmetic.customer.dto.CustomerRequest;
import com.aithmetic.customer.dto.CustomerResponse;
import com.aithmetic.customer.exception.ValidateRequestBody;
import com.aithmetic.customer.exception.ValidationCheckException;
import com.aithmetic.customer.model.Customer;
import com.aithmetic.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;
    private final ValidateRequestBody validateRequestBody;

    public void createCustomer(CustomerRequest customerRequest){
        log.debug("Create request : {}" ,customerRequest);
        validateCustomerExists(customerRequest);
        validateRequestBody.customerFieldsValidation(customerRequest);

        Customer customer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .username(customerRequest.getUsername())
                .password(customerRequest.getPassword())
                .dateOfBirth(customerRequest.getDateOfBirth())
                .gender(customerRequest.getGender())
                .build();
        customerRepository.save(customer);
        log.info("Customer {} is saved",customer);
    }

    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        log.error("No customers");
        log.info("Customer list : {}",customers);
        return customers.stream().map(this::mapToCustomerResponse).toList();
    }

    private CustomerResponse mapToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .dateOfBirth(customer.getDateOfBirth())
                .gender(customer.getGender())
                .build();
    }

    public void validateCustomerExists(CustomerRequest customerRequest) {
        if (customerRepository.existsByUsername(customerRequest.getUsername())) {
            throw new IllegalArgumentException("Username is already in use. Please choose a different username.");
        }
        if (customerRepository.existsByEmail(customerRequest.getEmail())) {
            throw new IllegalArgumentException("Email is already in use. Please choose a different email.");
        }
    }
}
