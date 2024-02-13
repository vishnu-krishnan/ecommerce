package com.aithmetic.product.service;

import com.aithmetic.product.dto.ProductRequest;
import com.aithmetic.product.dto.ProductResponse;
import com.aithmetic.product.model.Product;
import com.aithmetic.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ValidateProductRequestBody validateProductRequestBody;


    public void createProduct(ProductRequest productRequest)
    {
        validateProductRequestBody.validateProductRequestBody(productRequest);
        Product product = Product.builder()
                .productId(productRequest.getProductId())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved",product);
    }
    public List<ProductResponse> getAllProducts() {
       List<Product> products =  productRepository.findAll();
        if (products.isEmpty()) {
            log.error("No products found");
        }
       return products.stream().map(this::mapToProductResponse).toList();
    }
    public List<ProductResponse> getProduct(String productId) {
        List<Product> products = productRepository.findByProductId(productId);

        if (!products.isEmpty()) {
            log.info("Product is available");
            return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
        } else {
            log.debug("No products found for productId: {}", productId);
            return Collections.emptyList();
        }
    }
    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }



}
