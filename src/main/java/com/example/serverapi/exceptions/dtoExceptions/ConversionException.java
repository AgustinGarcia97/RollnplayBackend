package com.example.serverapi.exceptions.dtoExceptions;

public class ConversionException extends RuntimeException {
    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
