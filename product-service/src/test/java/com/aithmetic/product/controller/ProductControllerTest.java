package com.aithmetic.product.controller;

import com.aithmetic.product.dto.ProductRequest;
import com.aithmetic.product.dto.ProductResponse;
import com.aithmetic.product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    private static final Logger log = LoggerFactory.getLogger(ProductControllerTest.class);


    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void createProduct_shouldReturnCreatedResponse() {
        ProductRequest productRequest = new ProductRequest();
        doNothing().when(productService).createProduct(productRequest);
        ResponseEntity<String> responseEntity = productController.createProduct(productRequest);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals("Product null created successfully", responseEntity.getBody());
        log.info("createProduct test passed");
    }

    @Test
    void getAllProducts_shouldReturnProducts(){

        List<ProductResponse> productResponses = Collections.singletonList(new ProductResponse());
        when(productService.getAllProducts()).thenReturn(productResponses);

        ResponseEntity<Object> responseEntity = productController.getAllProducts();
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(productResponses, responseEntity.getBody());
        log.info("getAllProducts test passed");
    }

    @Test
    void getProduct_shouldReturnProducts(){
        List<ProductResponse> productResponses = Collections.singletonList(new ProductResponse());
        when(productService.getProduct("productId")).thenReturn(productResponses);

        ResponseEntity<Object> responseEntity = productController.getProduct("productId");
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(productResponses, responseEntity.getBody());
        log.info("getProduct By productId test passed");
    }

}
