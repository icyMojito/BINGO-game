package com.cool.exception;

public class InvalidBingoSizeException extends RuntimeException {
    public InvalidBingoSizeException(String message) {
        super(message);
    }
}
