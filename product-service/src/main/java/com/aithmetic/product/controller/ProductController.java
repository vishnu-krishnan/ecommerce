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
@RequestMapping(value = "/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<Object> getAllProducts() {
        try {
            List<ProductResponse> productResponses = productService.getAllProducts();
            if (productResponses.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data available");
            }
            return ResponseEntity.ok(productResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching products: "+e.getMessage());
        }
    }
}
