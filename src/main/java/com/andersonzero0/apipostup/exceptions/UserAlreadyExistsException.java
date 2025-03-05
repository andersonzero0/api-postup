package com.andersonzero0.apipostup.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message == null ? "User already exists" : message);
    }
}
