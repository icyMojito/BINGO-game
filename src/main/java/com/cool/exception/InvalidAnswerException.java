package com.cool.exception;

public class InvalidAnswerException extends RuntimeException {
    public InvalidAnswerException(String message) {
        super(message);
    }
}
