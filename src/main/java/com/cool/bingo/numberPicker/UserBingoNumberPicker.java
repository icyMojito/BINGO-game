package com.cool.bingo.numberPicker;

import com.cool.bingo.BingoSize;
import com.cool.bingo.BingoType;
import com.cool.bingo.number.BingoNumber;
import com.cool.exception.InvalidBingoNumberException;
import com.cool.view.InputView;
import com.cool.view.OutputView;

import java.io.IOException;

public class UserBingoNumberPicker implements BingoNumberPicker {
    private final BingoType bingoType;
    private final BingoSize bingoSize;

    public UserBingoNumberPicker(BingoType bingoType, BingoSize bingoSize) {
        this.bingoType = bingoType;
        this.bingoSize = bingoSize;
    }

    @Override
    public BingoNumber pickBingoNumber() throws IOException {
        OutputView.printRequestToMarkBingoNumber(bingoType, bingoSize);
        String bingoNumberToMarkValue = InputView.requestPlayerInput();
        try {
            return BingoNumber.of(bingoNumberToMarkValue, bingoSize);
        } catch (InvalidBingoNumberException | IllegalArgumentException e) {
            OutputView.printNoticeForInvalidBingoNumber(e.getMessage());
            return pickBingoNumber();
        }
    }
}
