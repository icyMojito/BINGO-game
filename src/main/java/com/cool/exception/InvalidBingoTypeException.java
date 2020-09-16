package com.cool.exception;

public class InvalidBingoTypeException extends RuntimeException {
    public InvalidBingoTypeException(String bingoCode) {
        super("존재하지 않는 Bingo Type입니다! -> " + bingoCode);
    }
}
