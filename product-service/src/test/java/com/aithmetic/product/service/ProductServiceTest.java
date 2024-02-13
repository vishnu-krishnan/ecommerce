package com.aithmetic.product.service;

import com.aithmetic.product.dto.ProductRequest;
import com.aithmetic.product.dto.ProductResponse;
import com.aithmetic.product.model.Product;
import com.aithmetic.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ValidateProductRequestBody validateProductRequestBody;

    @InjectMocks
    private ProductService productService;

    @Test
    void createProduct() {
        ProductRequest productRequest = getProductRequest();
        Product expectedProduct = getProductEntity();

        doNothing().when(validateProductRequestBody).validateProductRequestBody(any());
        when(productRepository.save(any())).thenReturn(expectedProduct);

        productService.createProduct(productRequest);

        verify(validateProductRequestBody, times(1)).validateProductRequestBody(productRequest);
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void getAllProducts() {
        Product product = getProductEntity();
        List<Product> productList = Arrays.asList(product);

        when(productRepository.findAll()).thenReturn(productList);

        List<ProductResponse> result = productService.getAllProducts();

        assertEquals(1, result.size());
    }

    @Test
    void getProduct() {
        String productId = "123";
        Product product = getProductEntity();
        List<Product> productList = Arrays.asList(product);

        when(productRepository.findByProductId(productId)).thenReturn(productList);

        List<ProductResponse> result = productService.getProduct(productId);

        assertEquals(1, result.size());
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .productId("123")
                .name("iphone 13")
                .description("Iphone 13 black")
                .price(BigDecimal.valueOf(50000))
                .quantity(10)
                .build();
    }

    private Product getProductEntity() {
        return Product.builder()
                .productId("123")
                .name("iphone 13")
                .description("Iphone 13 black")
                .price(BigDecimal.valueOf(50000))
                .quantity(10)
                .build();
    }
}
