package com.aithmetic.order.service;


import com.aithmetic.customer.dto.orderRequest;
import com.aithmetic.customer.exception.ValidationCheckException;
import com.aithmetic.order.dto.OrderRequest;
import com.aithmetic.order.exception.ValidationCheckException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ValidateRequestBody {

    public void customerFieldsValidation(OrderRequest orderRequest) {

        validateNotBlank("Customer ID", orderRequest.getCustomerId());
        validateNotBlank("Product ID", orderRequest.getProductId());
        validateNotBlank("Product Name", orderRequest.getProductName());
        validateNotZero("Quantity", orderRequest.getQuantity());
        validateNotNull("Unit Price", orderRequest.getUnitPrice());
        validateNotNull("Total Price", orderRequest.getTotalPrice());
        validateNotNull("Order Date", orderRequest.getOrderDate());
        validateNotBlank("Shipping Address", orderRequest.getShippingAddress());
        validateNotBlank("Shipping Method", orderRequest.getShippingMethod());
        validateNotBlank("Order Status", orderRequest.getOrderStatus());
    }
        private void validateNotBlank(String fieldName, String value) {
            if (value == null || value.trim().isEmpty()) {
                throw new ValidationCheckException(fieldName + " must not be blank");
            }
        }

        private void validateNotZero(String fieldName, int value) {
            if (value == 0) {
                throw new ValidationCheckException(fieldName + " must not be zero");
            }
        }

        private void validateNotNull(String fieldName, Object value) {
            if (value == null) {
                throw new ValidationCheckException(fieldName + " must not be null");
            }
        }
}
