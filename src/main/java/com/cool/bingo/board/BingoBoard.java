package com.cool.bingo.board;

import com.cool.bingo.PlayerType;
import com.cool.bingo.number.BingoNumber;

import java.util.Arrays;

public abstract class BingoBoard {
    protected final PlayerType playerType;
    protected final BingoNumber[][] bingoNumbers;

    public BingoBoard(PlayerType playerType, BingoNumber[][] bingoNumbers) {
        this.playerType = playerType;
        this.bingoNumbers = bingoNumbers;
    }

    public void mark(BingoNumber bingoNumberToMark) {
        Arrays.stream(this.bingoNumbers)
                .flatMap(Arrays::stream)
                .filter(bingoNumber -> bingoNumber.equals(bingoNumberToMark))
                .findFirst()
                .ifPresent(BingoNumber::mark);
    }

    public boolean isUserPlayer() {
        return this.playerType == PlayerType.USER;
    }

    public abstract boolean isBingo();

    public BingoNumber[][] getBingoNumbers() {
        return this.bingoNumbers;
    }

    public PlayerType getPlayerType() {
        return this.playerType;
    }
}
