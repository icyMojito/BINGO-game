package com.cool.bingo.board;

import com.cool.bingo.BingoSize;
import com.cool.bingo.BingoType;
import com.cool.bingo.PlayerType;
import com.cool.bingo.number.BingoNumber;
import com.cool.bingo.number.BingoNumbers;
import com.cool.bingo.numberPicker.BingoNumberPicker;
import com.cool.bingo.numberPicker.BingoNumberPickerFactory;

import java.util.Arrays;

public class BlackBingoBoard extends BingoBoard {

    private BlackBingoBoard(PlayerType playerType, BingoNumber[][] bingoNumbers, BingoNumberPicker bingoNumberPicker) {
        super(playerType, bingoNumbers, bingoNumberPicker);
    }

    public static BlackBingoBoard of(BingoSize bingoSize, BingoType bingoType, PlayerType playerType,
                                     BingoNumbers bingoNumbers) {
        BingoNumber[][] bingoNums = bingoNumbers.create2dBingoNumbers(bingoSize);
        BingoNumberPicker bingoNumberPicker = BingoNumberPickerFactory.createBingoNumberPicker(playerType, bingoType,
                                                                                               bingoSize, bingoNums);

        return new BlackBingoBoard(playerType, bingoNums, bingoNumberPicker);
    }

    @Override
    public boolean isBingo() {
        return Arrays.stream(this.bingoNumbers)
                .flatMap(Arrays::stream)
                .allMatch(BingoNumber::isMarked);
    }
}
