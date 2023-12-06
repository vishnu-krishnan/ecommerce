package com.aithmetic.product.service;

import com.aithmetic.product.ValidationCheckException;
import com.aithmetic.product.dto.ProductRequest;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class ValidateProductRequestBody {

    public void validateProductRequestBody(ProductRequest productRequest){
        validateNotBlank("Product name", productRequest.getName());
        validateNotBlank("Product description", productRequest.getDescription());
        validateNotNull("Price", productRequest.getPrice());
        validateNotZero("Quantity", productRequest.getQuantity());
    }
    private void validateNotBlank(String fieldName, String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationCheckException(fieldName + " must not be blank");
        }
    }
    private void validateNotZero(String fieldName, BigInteger value) {
        if (value.compareTo(BigInteger.ZERO) == 0) {
            throw new ValidationCheckException(fieldName + " must not be zero");
        }
    }
    private void validateNotNull(String fieldName, Object value) {
        if (value == null) {
            throw new ValidationCheckException(fieldName + " must not be null");
        }
    }
}
