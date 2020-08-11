package com.cool.bingo;

import com.cool.exception.InvalidBingoTypeException;

public class BingoType {
    private static final String RANDOM = "R";
    private static final String BLACK = "B";
    private static final String NUMBER = "N";
    private static final String[] typeLetter = {BLACK, NUMBER};
    private static final InvalidBingoTypeException INVALID_BINGO_TYPE_EXCEPTION = new InvalidBingoTypeException();

    private final String type;

    private BingoType(String playerAnswer) {
        validateAnswer(playerAnswer);
        this.type = transformType(playerAnswer);
    }

    public static BingoType from(String playerAnswer) {
        return new BingoType(playerAnswer);
    }

    private void validateAnswer(String playerAnswer) {
        playerAnswer = playerAnswer.toUpperCase();
        if (!playerAnswer.equals(BLACK) && !playerAnswer.equals(NUMBER) && !playerAnswer.equals(RANDOM)) {
            throw INVALID_BINGO_TYPE_EXCEPTION;
        }
    }

    private String transformType(String playerAnswer) {
        if (playerAnswer.equals(RANDOM)) {
            int i = (int) (Math.random() * 2);
            return typeLetter[i];
        }

        return playerAnswer.equals(BLACK) ? BLACK : NUMBER;
    }
}
