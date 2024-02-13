package com.aithmetic.product.repository;

import com.aithmetic.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findByProductId(String productId);


}
