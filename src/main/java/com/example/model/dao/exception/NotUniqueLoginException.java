package com.example.model.dao.exception;

public class NotUniqueLoginException extends Exception{
    public NotUniqueLoginException() {
        super("Not unique login");
    }

    public NotUniqueLoginException(String message) {
        super(message);
    }

    public NotUniqueLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotUniqueLoginException(Throwable cause) {
        super(cause);
    }
}
