package com.aithmetic.product.controller;

import com.aithmetic.product.dto.ProductRequest;
import com.aithmetic.product.dto.ProductResponse;
import com.aithmetic.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ProductController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    public static final String BASE_URL = "/product";

    @Autowired
    private final ProductService productService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest) {
        try {
            productService.createProduct(productRequest);
            String responseMessage = String.format("Product %s created successfully", productRequest.getName());
            log.info(responseMessage);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } catch (Exception e) {
            String errorMessage = "Error creating product: " + e.getMessage();
            log.error(errorMessage, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<Object> getAllProducts() {
        try {
            List<ProductResponse> productResponses = productService.getAllProducts();
            if (productResponses.isEmpty()) {
                String message = "No data available";
                log.warn(message);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
            log.info("Retrieved {} products", productResponses.size());
            return ResponseEntity.ok(productResponses);
        } catch (Exception e) {
            String errorMessage = "Error fetching products: " + e.getMessage();
            log.error(errorMessage, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping(value = "/get/{productId}")
    public ResponseEntity<Object> getProduct(@PathVariable String productId) {
        try {
            List<ProductResponse> productResponses = productService.getProduct(productId);
            if (productResponses.isEmpty()) {
                String message = "Product not available";
                log.info(message);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
            log.info("Retrieved {} products for productId: {}", productResponses.size(), productId);
            return ResponseEntity.ok(productResponses);
        } catch (Exception e) {
            String errorMessage = "Error fetching product: " + e.getMessage();
            log.error(errorMessage, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
