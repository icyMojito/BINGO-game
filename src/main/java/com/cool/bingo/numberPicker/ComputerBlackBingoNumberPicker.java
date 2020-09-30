package com.cool.bingo.numberPicker;

import com.cool.bingo.number.BingoNumber;
import com.cool.exception.InvalidBingoNumberException;

import java.util.Arrays;

public class ComputerBlackBingoNumberPicker implements ComputerBingoNumberPicker {
    private final BingoNumber[][] bingoNumbers;

    public ComputerBlackBingoNumberPicker(BingoNumber[][] bingoNumbers) {
        this.bingoNumbers = bingoNumbers;
    }

    @Override
    public BingoNumber pickBingoNumber() {
        return Arrays.stream(bingoNumbers)
                .flatMap(Arrays::stream)
                .filter(BingoNumber::isNumber)
                .findFirst()
                .orElseThrow(() -> new InvalidBingoNumberException("더 이상 남아있는 숫자가 없습니다!"));
    }
}
