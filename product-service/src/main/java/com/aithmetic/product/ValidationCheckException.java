package com.aithmetic.product;

public class ValidationCheckException extends RuntimeException{
    public ValidationCheckException(String message){
        super(message);
    }
    public ValidationCheckException(String message, Throwable cause){
        super(message, cause);
    }
}
