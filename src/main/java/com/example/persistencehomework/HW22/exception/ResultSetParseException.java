package com.example.persistencehomework.HW22.exception;

public class ResultSetParseException extends RuntimeException {
    public ResultSetParseException(String message, Exception e) {
        super(message, e);
    }
}
