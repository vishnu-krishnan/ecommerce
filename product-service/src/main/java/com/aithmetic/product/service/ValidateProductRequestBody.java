package com.aithmetic.product.service;

import com.aithmetic.product.exception.ValidationCheckException;
import com.aithmetic.product.dto.ProductRequest;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class ValidateProductRequestBody {

    public void validateProductRequestBody(ProductRequest productRequest){
        validateNotBlank("Product id", productRequest.getProductId());
        validateNotBlank("Product name", productRequest.getName());
        validateNotBlank("Product description", productRequest.getDescription());
        validateNotNull("Price", productRequest.getPrice());
        validateNotZero("Quantity", productRequest.getQuantity());
    }
    private void validateNotBlank(String fieldName, String value) {
        if (isBlank(value)) {
            throw new ValidationCheckException(fieldName + " must not be blank");
        }
    }
    private void validateNotZero(String fieldName, Integer value) {
        if (value == 0) {
            throw new ValidationCheckException(fieldName + " must not be zero");
        }
    }
    private void validateNotNull(String fieldName, Object value) {
        if (value == null) {
            throw new ValidationCheckException(fieldName + " must not be null");
        }
    }
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
