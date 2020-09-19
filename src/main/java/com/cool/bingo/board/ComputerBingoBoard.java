package com.cool.bingo.board;

import com.cool.bingo.BingoNumber;
import com.cool.bingo.BingoNumbers;
import com.cool.bingo.BingoSize;

public class ComputerBingoBoard extends BingoBoard {
    private final BingoSize bingoSize;

    public ComputerBingoBoard(BingoSize bingoSize) {
        super(BingoNumbers.createRandomBingoNumbers(bingoSize));
        this.bingoSize = bingoSize;
    }

    public BingoNumber getBingoNumberToRemove() {
        return super.bingoNumbers.getRandomBingoNumber();
    }

    public BingoNumbers getBingoNumbers() {
        return bingoNumbers;
    }
}
