package com.example.persistencehomework.HW22.exception;

public class DaoOperationException extends RuntimeException {
    public DaoOperationException(String message, Exception e) {
        super(message, e);
    }
}
