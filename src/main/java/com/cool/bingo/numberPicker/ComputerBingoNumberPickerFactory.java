package com.cool.bingo.numberPicker;

import com.cool.bingo.BingoType;
import com.cool.bingo.number.BingoNumber;

public class ComputerBingoNumberPickerFactory {
    public static ComputerBingoNumberPicker createBingoNumberPicker(BingoType bingoType, BingoNumber[][] bingoNumbers) {
        if (BingoType.BLACK == bingoType) {
            return new ComputerBlackBingoNumberPicker(bingoNumbers);
        }
        return new ComputerNumberBingoNumberPicker(bingoNumbers);
    }
}
