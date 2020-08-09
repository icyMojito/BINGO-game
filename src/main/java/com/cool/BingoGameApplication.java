package com.cool;

import com.cool.bingo.BingoSize;
import com.cool.exception.InvalidBingoSizeException;
import com.cool.view.InputView;
import com.cool.view.OutputView;

import java.io.IOException;

public class BingoGameApplication {
    public static void main(String[] args) throws IOException {
        BingoSize bingoSize = fixBingoSize();
    }

    private static BingoSize fixBingoSize() throws IOException {
        while (true) {
            OutputView.printRequestForBingoSize();
            String playerAnswer = InputView.requestPlayerAnswer();
            try {
                return BingoSize.from(playerAnswer);
            } catch (InvalidBingoSizeException e) {
            }
        }
    }
}
