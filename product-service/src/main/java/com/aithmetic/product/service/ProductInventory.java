package com.aithmetic.product.service;

import com.aithmetic.product.model.Product;
import com.aithmetic.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductInventory {

    @Autowired
    private final ProductRepository productRepository;

    public boolean checkProductQuantity(String productId, int requestedQuantity) {
        log.info("checking product quantity");
        List<Product> productDetail = productRepository.findByProductId(productId);
        if (!productDetail.isEmpty()) {
            Product product = productDetail.get(0);
            int availableQuantity = product.getQuantity();
            log.info("Product {} has {} units available", productId, availableQuantity);
            return availableQuantity >= requestedQuantity;
        } else {
            log.info("No product found for productId: {}", productId);
            return false;
        }
    }
}
