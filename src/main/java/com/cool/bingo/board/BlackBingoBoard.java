package com.cool.bingo.board;

import com.cool.bingo.BingoSize;
import com.cool.bingo.PlayerType;
import com.cool.bingo.number.BingoNumber;
import com.cool.bingo.number.BingoNumbers;

import java.util.Arrays;

public class BlackBingoBoard extends BingoBoard {

    private BlackBingoBoard(PlayerType playerType, BingoNumber[][] bingoNumbers) {
        super(playerType, bingoNumbers);
    }

    public static BlackBingoBoard of(BingoSize bingoSize, PlayerType playerType, BingoNumbers bingoNumbers) {
        BingoNumber[][] bingoNums = bingoNumbers.create2dBingoNumbers(bingoSize);

        return new BlackBingoBoard(playerType, bingoNums);
    }

    @Override
    public boolean isBingo() {
        return Arrays.stream(this.bingoNumbers)
                .flatMap(Arrays::stream)
                .allMatch(BingoNumber::isMarked);
    }
}
