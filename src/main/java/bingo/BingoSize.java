package bingo;

import exception.InvalidBingoSizeException;

public class BingoSize {
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 8;

    private final int value;

    private BingoSize(String playerAnswer) {
        if (isNotNumber(playerAnswer) || isNotAllowedNumber(playerAnswer)) {
            throw new InvalidBingoSizeException();
        }
        this.value = Integer.parseInt(playerAnswer);
    }

    public static BingoSize from(String playerAnswer) {
        return new BingoSize(playerAnswer);
    }

    private boolean isNotNumber(String playerAnswer) {
        try {
            Integer.parseInt(playerAnswer);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    private boolean isNotAllowedNumber(String playerAnswer) {
        int number = Integer.parseInt(playerAnswer);

        return number < MIN_SIZE || MAX_SIZE < number;
    }
}
