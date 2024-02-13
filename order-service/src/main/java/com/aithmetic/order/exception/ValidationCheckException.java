package com.aithmetic.order.exception;

public class ValidationCheckException extends RuntimeException{
    public ValidationCheckException (String message){
        super(message);
    }
    public ValidationCheckException(String message, Throwable cause){
        super(message, cause);
    }
}
