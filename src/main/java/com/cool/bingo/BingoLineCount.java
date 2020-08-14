package com.cool.bingo;

import com.cool.exception.InvalidBingoLineCountException;

public class BingoLineCount {
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 8;
    private static final InvalidBingoLineCountException INVALID_BINGO_LINE_COUNT_EXCEPTION = new InvalidBingoLineCountException();

    private final int value;

    private BingoLineCount(String playerAnswer) {
        if (isNotNumber(playerAnswer) || isNotAllowedNumber(playerAnswer)) {
            throw INVALID_BINGO_LINE_COUNT_EXCEPTION;
        }
        this.value = Integer.parseInt(playerAnswer);
    }

    public static BingoLineCount from(String playerAnswer) {
        return new BingoLineCount(playerAnswer);
    }

    private boolean isNotNumber(String playerAnswer) {
        for (int i = 0; i < playerAnswer.length(); i++) {
            char c = playerAnswer.charAt(i);
            if (c < '0' || '9' < c) {
                return true;
            }
        }
        return false;
    }

    private boolean isNotAllowedNumber(String playerAnswer) {
        int number = Integer.parseInt(playerAnswer);

        return number < MIN_SIZE || MAX_SIZE < number;
    }
}
