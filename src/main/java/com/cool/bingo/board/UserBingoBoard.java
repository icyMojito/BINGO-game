package com.cool.bingo.board;

import com.cool.bingo.BingoNumber;
import com.cool.bingo.BingoNumbers;
import com.cool.bingo.BingoSize;

public class UserBingoBoard extends BingoBoard {
    private final BingoSize bingoSize;

    public UserBingoBoard(BingoSize bingoSize) {
        super(BingoNumbers.empty());
        this.bingoSize = bingoSize;
    }

    public void addNumbers(BingoNumbers bingoNumbers) {
        BingoNumbers newBingoNumbers = this.bingoNumbers.add(bingoNumbers);
        int sizeGap = newBingoNumbers.calculateSizeGap(bingoSize);
        if (0 < sizeGap) {
            newBingoNumbers = newBingoNumbers.reduce(bingoSize);
        }
        this.bingoNumbers = newBingoNumbers;
    }

    public boolean hasNeedMoreNumbers() {
        int sizeGap = this.bingoNumbers.calculateSizeGap(this.bingoSize);
        return sizeGap < 0;
    }

    public void markBingoNumber(BingoNumber bingoNumberToRemove) {
        this.bingoNumbers.markBingoNumber(bingoNumberToRemove);
    }

    public boolean isBlackBingo() {
        return this.bingoNumbers.isAllRemoved();
    }

    public BingoNumbers getBingoNumbers() {
        return bingoNumbers;
    }
}
