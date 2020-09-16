package com.cool;

import com.cool.bingo.BingoBoard;
import com.cool.bingo.BingoNumbers;
import com.cool.bingo.BingoSize;
import com.cool.bingo.BingoType;
import com.cool.view.InputView;
import com.cool.view.OutputView;

import java.io.IOException;

public class BingoGameApplication {
    public static void main(String[] args) throws IOException {
        BingoSize bingoSize = createBingoSize();
        BingoType bingoType = createBingoType();
        BingoBoard userBingoBoard = new BingoBoard(bingoSize);
        addNumbersToBingoBoard(userBingoBoard, bingoSize);
    }

    private static BingoSize createBingoSize() throws IOException {
        while (true) {
            OutputView.printRequestForBingoSize();
            String bingoSize = InputView.requestPlayerInput();
            try {
                return BingoSize.from(bingoSize);
            } catch (RuntimeException e) {
                OutputView.printExceptionMessage(e);
            }
        }
    }

    private static BingoType createBingoType() throws IOException {
        while (true) {
            OutputView.printRequestForBingoType();
            String bingoType = InputView.requestPlayerInput();
            try {
                return BingoType.from(bingoType);
            } catch (RuntimeException e) {
                OutputView.printExceptionMessage(e);
            }
        }
    }

    private static void addNumbersToBingoBoard(BingoBoard bingoBoard, BingoSize bingoSize) throws IOException {
        OutputView.printRequestForBingoNumber(bingoSize);

        while (bingoBoard.hasNeedMoreNumbers()) {
            OutputView.printRequestForNumberInput();
            String bingoNumbersValue = InputView.requestPlayerInput();
            try {
                BingoNumbers bingoNumbers = BingoNumbers.of(bingoNumbersValue, bingoSize);
                bingoBoard.addNumbers(bingoNumbers);
                OutputView.printBingoBoard(bingoSize, bingoBoard.getBingoNumbers());
            } catch (RuntimeException e) {
                OutputView.printExceptionMessage(e);
                OutputView.printRequestForBingoNumber(bingoSize);
            }
        }
    }
}
