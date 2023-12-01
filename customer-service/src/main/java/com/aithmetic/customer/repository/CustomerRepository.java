package com.aithmetic.customer.repository;

import com.aithmetic.customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
