package com.cool.bingo.board;

import com.cool.bingo.BingoSize;
import com.cool.bingo.BingoType;
import com.cool.bingo.PlayerType;
import com.cool.bingo.number.BingoNumber;
import com.cool.bingo.number.BingoNumbers;
import com.cool.bingo.numberPicker.BingoNumberPicker;
import com.cool.bingo.numberPicker.BingoNumberPickerFactory;

public class NumberBingoBoard extends BingoBoard {
    private final BingoSize bingoSize;

    private NumberBingoBoard(BingoSize bingoSize, PlayerType playerType, BingoNumber[][] bingoNumbers,
                             BingoNumberPicker bingoNumberPicker) {
        super(playerType, bingoNumbers, bingoNumberPicker);
        this.bingoSize = bingoSize;
    }

    public static NumberBingoBoard of(BingoSize bingoSize, BingoType bingoType, PlayerType playerType,
                                      BingoNumbers bingoNumbers) {
        BingoNumber[][] bingoNums = bingoNumbers.create2dBingoNumbers(bingoSize);
        BingoNumberPicker bingoNumberPicker = BingoNumberPickerFactory.createBingoNumberPicker(playerType, bingoType,
                                                                                               bingoSize, bingoNums);

        return new NumberBingoBoard(bingoSize, playerType, bingoNums, bingoNumberPicker);
    }

    @Override
    public boolean isBingo() {
        return this.bingoSize.isSmallerAndEqualThan(countMarkedLine());
    }

    public int countMarkedLine() {
        int horizontalMarkedLine = countHorizontalMarkedLine();
        int verticalMarkedLine = countVerticalMarkedLine();
        int crossMarkedLine = countCrossMarkedLine();

        return horizontalMarkedLine + verticalMarkedLine + crossMarkedLine;
    }

    private int countHorizontalMarkedLine() {
        int horizontalMarkedLine = 0;

        for (int row = 0; row < this.bingoNumbers.length; row++) {
            int markedNumber = 0;
            for (int col = 0; col < this.bingoNumbers[row].length; col++) {
                if (this.bingoNumbers[row][col].isNumber()) {
                    break;
                }
                markedNumber++;
            }

            if (markedNumber == this.bingoNumbers.length) {
                horizontalMarkedLine++;
            }
        }

        return horizontalMarkedLine;
    }

    private int countVerticalMarkedLine() {
        int verticalMarkedLine = 0;

        for (int col = 0; col < this.bingoNumbers.length; col++) {
            int markedNumber = 0;
            for (int row = 0; row < this.bingoNumbers[col].length; row++) {
                if (this.bingoNumbers[row][col].isNumber()) {
                    break;
                }
                markedNumber++;
            }

            if (markedNumber == this.bingoNumbers.length) {
                verticalMarkedLine++;
            }
        }

        return verticalMarkedLine;
    }

    private int countCrossMarkedLine() {
        int crossMarkedLine = 0;

        if (hasCrossMarkedLine()) {
            crossMarkedLine++;
        }

        if (hasReversedCrossMarkedLine()) {
            crossMarkedLine++;
        }

        return crossMarkedLine;
    }

    private boolean hasCrossMarkedLine() {
        int markedNumber = 0;
        for (int i = 0; i < this.bingoNumbers.length; i++) {
            if (this.bingoNumbers[i][i].isMarked()) {
                markedNumber++;
            }
        }

        return markedNumber == this.bingoNumbers.length;
    }

    private boolean hasReversedCrossMarkedLine() {
        int markedNumber = 0;
        for (int row = 0; row < this.bingoNumbers.length; row++) {
            for (int col = this.bingoNumbers.length - 1; 0 <= col; col--) {
                if (this.bingoNumbers[row][col].isMarked()) {
                    markedNumber++;
                }
            }
        }

        return markedNumber == this.bingoNumbers.length;
    }
}