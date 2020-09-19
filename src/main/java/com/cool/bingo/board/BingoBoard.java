package com.cool.bingo.board;

import com.cool.bingo.BingoNumber;
import com.cool.bingo.BingoNumbers;

public class BingoBoard {
    protected BingoNumbers bingoNumbers;

    public BingoBoard(BingoNumbers bingoNumbers) {
        this.bingoNumbers = bingoNumbers;
    }

    public void markBingoNumber(BingoNumber bingoNumberToRemove) {
        this.bingoNumbers.markBingoNumber(bingoNumberToRemove);
    }

    public boolean isBlackBingo() {
        return this.bingoNumbers.isAllRemoved();
    }
}
