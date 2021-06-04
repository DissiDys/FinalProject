package com.example.model.dao.exception;

public class NotUniqueInsertionException extends Exception {
    public NotUniqueInsertionException() {
        super("Not unique insertion");
    }

    public NotUniqueInsertionException(String message) {
        super(message);
    }

    public NotUniqueInsertionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotUniqueInsertionException(Throwable cause) {
        super(cause);
    }
}
