package com.cool.bingo.numberPicker;

import com.cool.bingo.number.BingoNumber;
import com.cool.exception.InvalidBingoNumberException;
import com.cool.view.OutputView;

import java.io.IOException;
import java.util.Arrays;

public class ComputerBlackBingoNumberPicker implements BingoNumberPicker {
    private final BingoNumber[][] bingoNumbers;

    public ComputerBlackBingoNumberPicker(BingoNumber[][] bingoNumbers) {
        this.bingoNumbers = bingoNumbers;
    }

    @Override
    public BingoNumber pickBingoNumber() throws IOException {
        BingoNumber bingoNumber = Arrays.stream(bingoNumbers)
                .flatMap(Arrays::stream)
                .filter(BingoNumber::isNumber)
                .findFirst()
                .orElseThrow(() -> new InvalidBingoNumberException("더 이상 남아있는 숫자가 없습니다!"));

        OutputView.printComputerBingoNumberToMark(bingoNumber);

        return bingoNumber;
    }
}
