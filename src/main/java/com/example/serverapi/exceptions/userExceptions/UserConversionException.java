package com.example.serverapi.exceptions.userExceptions;

public class UserConversionException extends RuntimeException{
    public UserConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
