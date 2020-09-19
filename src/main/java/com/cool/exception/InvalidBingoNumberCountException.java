package com.cool.exception;

public class InvalidBingoNumberCountException extends RuntimeException {
    public InvalidBingoNumberCountException(String message) {
        super(message);
    }
}
