package com.cool;

import com.cool.bingo.BingoSize;
import com.cool.bingo.BingoType;
import com.cool.exception.InvalidBingoSizeException;
import com.cool.exception.InvalidBingoTypeException;
import com.cool.view.InputView;
import com.cool.view.OutputView;

import java.io.IOException;

public class BingoGameApplication {
    public static void main(String[] args) throws IOException {
        BingoSize bingoSize = createBingoSize();
        BingoType bingoType = createBingoType();
    }

    private static BingoSize createBingoSize() throws IOException {
        while (true) {
            OutputView.printRequestForBingoSize();
            String playerAnswer = InputView.requestPlayerAnswer();
            try {
                return BingoSize.from(playerAnswer);
            } catch (InvalidBingoSizeException e) {
            }
        }
    }

    private static BingoType createBingoType() throws IOException {
        while (true) {
            OutputView.printRequestForBingoType();
            String playerAnswer = InputView.requestPlayerAnswer();
            try {
                return BingoType.from(playerAnswer);
            } catch (InvalidBingoTypeException e) {
            }
        }
    }
}
