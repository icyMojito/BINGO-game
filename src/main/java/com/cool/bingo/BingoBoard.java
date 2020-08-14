package com.cool.bingo;

import com.cool.exception.InvalidBingoNumberException;

public class BingoBoard {
    private static final InvalidBingoNumberException INVALID_BINGO_NUMBER_EXCEPTION = new InvalidBingoNumberException();

    private static final int MIN_NUMBER = 1;
    private final BingoLineCount bingoLineCount;
    private final int maxNumber;
    private final int[][] cells;

    private BingoBoard(BingoLineCount bingoLineCount) {
        this.bingoLineCount = bingoLineCount;
        this.maxNumber = bingoLineCount.computeBingoMaxNumber();
        int bingoLineCountValue = bingoLineCount.getValue();
        this.cells = new int[bingoLineCountValue][bingoLineCountValue];
    }

    public static BingoBoard of(BingoLineCount bingoLineCount) {
        return new BingoBoard(bingoLineCount);
    }

    public void fillNumber(int order, String playerAnswer) {
        if (isNotNumber(playerAnswer) || isOutOfRange(playerAnswer) || isDuplicated(playerAnswer)) {
            throw INVALID_BINGO_NUMBER_EXCEPTION;
        }

        int row = order / bingoLineCount.getValue();
        int col = order % bingoLineCount.getValue();
        cells[row][col] = Integer.parseInt(playerAnswer);
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

    private boolean isOutOfRange(String playerAnswer) {
        int number = Integer.parseInt(playerAnswer);
        return number < MIN_NUMBER || maxNumber < number;
    }

    private boolean isDuplicated(String playerAnswer) {
        int number = Integer.parseInt(playerAnswer);
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                if (cells[row][col] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[][] getCells() {
        return cells;
    }
}
