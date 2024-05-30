package com.example.serverapi.exceptions;

public class SaleNotFoundException extends RuntimeException {
    public SaleNotFoundException(String message) {
        super("Sale not found");
    }
}
