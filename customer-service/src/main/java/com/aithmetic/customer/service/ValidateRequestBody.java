package com.aithmetic.customer.service;


import com.aithmetic.customer.dto.CustomerRequest;
import com.aithmetic.customer.exception.ValidationCheckException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ValidateRequestBody {

    public void customerFieldsValidation(CustomerRequest customerRequest){

        validateNotBlank("First Name", customerRequest.getFirstName());
        validateNotBlank("Last Name", customerRequest.getLastName());
        validateNotBlank("Email", customerRequest.getEmail());
        validateNotBlank("Username", customerRequest.getUsername());
        validateNotBlank("Password", customerRequest.getPassword());
        validateNotBlank("Gender", customerRequest.getGender());

        if (customerRequest.getDateOfBirth() == null) {
            throw new ValidationCheckException("Date of Birth must be provided");
        }
        if (!isValidEmail(customerRequest.getEmail())) {
            throw new ValidationCheckException("Invalid email format");
        }
    }
    private static void validateNotBlank(String fieldName, String value) {
        if (StringUtils.isBlank(value)) {
            throw new ValidationCheckException(fieldName + " must be provided");
        }
    }
    private static boolean isValidEmail(String email) {
        return email.matches(".+@.+\\..+");
    }
}
