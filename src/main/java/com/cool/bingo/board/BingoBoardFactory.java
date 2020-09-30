package com.cool.bingo.board;

import com.cool.bingo.BingoSize;
import com.cool.bingo.BingoType;
import com.cool.bingo.PlayerType;
import com.cool.bingo.number.BingoNumbers;

public class BingoBoardFactory {
    public static BingoBoard createBingoBoard(BingoSize bingoSize, BingoType bingoType, PlayerType playerType,
                                              BingoNumbers bingoNumbers) {
        if (BingoType.BLACK == bingoType) {
            return BlackBingoBoard.of(bingoSize, playerType, bingoNumbers);
        }
        return NumberBingoBoard.of(bingoSize, playerType, bingoNumbers);
    }
}
