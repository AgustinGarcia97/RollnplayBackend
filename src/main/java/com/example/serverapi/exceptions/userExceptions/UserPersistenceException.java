package com.example.serverapi.exceptions.userExceptions;

public class UserPersistenceException extends RuntimeException {
    public UserPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
