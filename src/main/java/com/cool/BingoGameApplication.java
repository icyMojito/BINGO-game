package com.cool;

import com.cool.bingo.BingoBoard;
import com.cool.bingo.BingoLineCount;
import com.cool.bingo.BingoType;
import com.cool.exception.InvalidBingoLineCountException;
import com.cool.exception.InvalidBingoTypeException;
import com.cool.view.InputView;
import com.cool.view.OutputView;

import java.io.IOException;

public class BingoGameApplication {
    public static void main(String[] args) throws IOException {
        BingoLineCount bingoLineCount = createBingoSize();
        BingoType bingoType = createBingoType();
        BingoBoard userBingoBoard = BingoBoard.of(bingoLineCount);
        fillUserBingoBoard(userBingoBoard, bingoLineCount);
    }

    private static BingoLineCount createBingoSize() throws IOException {
        while (true) {
            OutputView.printRequestForBingoSize();
            String playerAnswer = InputView.requestPlayerAnswer();
            try {
                return BingoLineCount.from(playerAnswer);
            } catch (InvalidBingoLineCountException ignored) {
            }
        }
    }

    private static BingoType createBingoType() throws IOException {
        while (true) {
            OutputView.printRequestForBingoType();
            String playerAnswer = InputView.requestPlayerAnswer();
            try {
                return BingoType.from(playerAnswer);
            } catch (InvalidBingoTypeException ignored) {
            }
        }
    }

    private static void fillUserBingoBoard(BingoBoard bingoBoard, BingoLineCount bingoLineCount) throws IOException {
        int totalBingoNumbersCount = bingoLineCount.computeTotalBingoNumbersCount();
        int maxNumber = bingoLineCount.computeBingoMaxNumber();

        OutputView.printRequestForBingoNumber(totalBingoNumbersCount, maxNumber);
        for (int order = 0; order < totalBingoNumbersCount; order++) {
            OutputView.printRequestForNumberInput();
            String playerAnswer = InputView.requestPlayerAnswer();
            bingoBoard.fillNumber(order, playerAnswer);
            OutputView.printBingoBoard(bingoBoard.getCells());
        }
    }
}
