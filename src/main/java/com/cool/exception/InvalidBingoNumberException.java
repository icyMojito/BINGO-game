package com.cool.exception;

public class InvalidBingoNumberException extends RuntimeException {
    public InvalidBingoNumberException(String s) {
        super(s);
    }
}
