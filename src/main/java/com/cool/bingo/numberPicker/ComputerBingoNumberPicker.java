package com.cool.bingo.numberPicker;

import com.cool.bingo.number.BingoNumber;

import java.io.IOException;

public interface ComputerBingoNumberPicker {
    BingoNumber pickBingoNumber() throws IOException;
}
