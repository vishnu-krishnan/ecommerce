package com.aithmetic.customer.repository;

import com.aithmetic.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
