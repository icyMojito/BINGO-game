package com.cool.bingo.numberPicker;

import com.cool.bingo.number.BingoNumber;
import com.cool.view.OutputView;

import java.io.IOException;
import java.util.Objects;

public class ComputerNumberBingoNumberPicker implements BingoNumberPicker {
    private final BingoNumber[][] bingoNumbers;

    public ComputerNumberBingoNumberPicker(BingoNumber[][] bingoNumbers) {
        this.bingoNumbers = bingoNumbers;
    }

    @Override
    public BingoNumber pickBingoNumber() throws IOException {
        for (int numberCount = 1; numberCount <= bingoNumbers.length; numberCount++) {
            BingoNumber bingoNumberInHorizontalLine = findBingoNumberInHorizontalLine(bingoNumbers, numberCount);
            if (Objects.nonNull(bingoNumberInHorizontalLine)) {
                OutputView.printComputerBingoNumberToMark(bingoNumberInHorizontalLine);
                return bingoNumberInHorizontalLine;
            }

            BingoNumber bingoNumberInVerticalLine = findBingoNumberInVerticalLine(bingoNumbers, numberCount);
            if (Objects.nonNull(bingoNumberInVerticalLine)) {
                OutputView.printComputerBingoNumberToMark(bingoNumberInVerticalLine);
                return bingoNumberInVerticalLine;
            }

            BingoNumber bingoNumberInCrossLine = findBingoNumberInCrossLine(bingoNumbers, numberCount);
            if (Objects.nonNull(bingoNumberInCrossLine)) {
                OutputView.printComputerBingoNumberToMark(bingoNumberInCrossLine);
                return bingoNumberInCrossLine;
            }

            BingoNumber bingoNumberInReversedCrossLine = findBingoNumberInReversedCrossLine(bingoNumbers, numberCount);
            if (Objects.nonNull(bingoNumberInReversedCrossLine)) {
                OutputView.printComputerBingoNumberToMark(bingoNumberInReversedCrossLine);
                return bingoNumberInReversedCrossLine;
            }
        }

        return null;
    }

    private BingoNumber findBingoNumberInHorizontalLine(BingoNumber[][] bingoNumbers, int numCount) {
        BingoNumber notMarkedBingoNumber = null;
        for (int row = 0; row < bingoNumbers.length; row++) {
            int numberCount = 0;
            for (int col = 0; col < bingoNumbers[row].length; col++) {
                if (bingoNumbers[row][col].isNumber()) {
                    numberCount++;
                    notMarkedBingoNumber = bingoNumbers[row][col];
                }
            }

            if (numberCount == numCount) {
                return notMarkedBingoNumber;
            }
        }

        return null;
    }

    private BingoNumber findBingoNumberInVerticalLine(BingoNumber[][] bingoNumbers, int numCount) {
        BingoNumber notMarkedBingoNumber = null;
        for (int col = 0; col < bingoNumbers.length; col++) {
            int numberCount = 0;
            for (int row = 0; row < bingoNumbers[col].length; row++) {
                if (bingoNumbers[row][col].isNumber()) {
                    numberCount++;
                    notMarkedBingoNumber = bingoNumbers[row][col];
                }
            }

            if (numberCount == numCount) {
                return notMarkedBingoNumber;
            }
        }

        return null;
    }

    private BingoNumber findBingoNumberInCrossLine(BingoNumber[][] bingoNumbers, int numCount) {
        int numberCount = 0;
        BingoNumber notMarkedBingoNumber = null;
        for (int row = 0; row < bingoNumbers.length; row++) {
            int col = row;
            if (bingoNumbers[row][col].isNumber()) {
                numberCount++;
                notMarkedBingoNumber = bingoNumbers[row][col];
            }
        }
        if (numberCount == numCount) {
            return notMarkedBingoNumber;
        }

        return null;
    }

    private BingoNumber findBingoNumberInReversedCrossLine(BingoNumber[][] bingoNumbers, int numCount) {
        int numberCount = 0;
        BingoNumber notMarkedBingoNumber = null;
        for (int row = 0; row < bingoNumbers.length; row++) {
            int col = bingoNumbers.length - row - 1;
            if (bingoNumbers[row][col].isNumber()) {
                numberCount++;
                notMarkedBingoNumber = bingoNumbers[row][col];
            }
        }
        if (numberCount == numCount) {
            return notMarkedBingoNumber;
        }

        return null;
    }
}
