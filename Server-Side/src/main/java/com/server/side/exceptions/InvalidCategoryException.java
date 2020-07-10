package com.server.side.exceptions;

public class InvalidCategoryException extends RuntimeException {

    public InvalidCategoryException() {

    }

    public InvalidCategoryException(String message) {
        super(message);
    }
}
