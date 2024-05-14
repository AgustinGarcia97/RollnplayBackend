package com.example.serverapi.exceptions;

public class UserDaoException extends RuntimeException {
    public UserDaoException(String message,Throwable cause) {
        super(message, cause);
    }
}
