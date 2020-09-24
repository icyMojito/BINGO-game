package com.cool.bingo.numberPicker;

import com.cool.bingo.BingoSize;
import com.cool.bingo.BingoType;
import com.cool.bingo.PlayerType;
import com.cool.bingo.number.BingoNumber;

public class BingoNumberPickerFactory {
    public static BingoNumberPicker createBingoNumberPicker(PlayerType playerType, BingoType bingoType,
                                                            BingoSize bingoSize, BingoNumber[][] bingoNumbers) {
        if (PlayerType.USER == playerType) {
            return new UserBingoNumberPicker(bingoType, bingoSize);
        }
        return createComputerBingoNumberPicker(bingoType, bingoNumbers);
    }

    private static BingoNumberPicker createComputerBingoNumberPicker(BingoType bingoType,
                                                                     BingoNumber[][] computer2dBingoNumbers) {
        if (BingoType.BLACK == bingoType) {
            return new ComputerBlackBingoNumberPicker(computer2dBingoNumbers);
        }
        return new ComputerNumberBingoNumberPicker(computer2dBingoNumbers);
    }
}
