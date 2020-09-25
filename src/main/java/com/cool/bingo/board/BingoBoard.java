package com.cool.bingo.board;

import com.cool.bingo.PlayerType;
import com.cool.bingo.number.BingoNumber;
import com.cool.bingo.numberPicker.BingoNumberPicker;
import com.cool.view.OutputView;

import java.io.IOException;
import java.util.Arrays;

public abstract class BingoBoard {
    protected final PlayerType playerType;
    protected final BingoNumber[][] bingoNumbers;
    protected final BingoNumberPicker bingoNumberPicker;

    public BingoBoard(PlayerType playerType, BingoNumber[][] bingoNumbers, BingoNumberPicker bingoNumberPicker) {
        this.playerType = playerType;
        this.bingoNumbers = bingoNumbers;
        this.bingoNumberPicker = bingoNumberPicker;
    }

    public void mark(BingoNumber bingoNumberToMark) throws IOException {
        Arrays.stream(this.bingoNumbers)
                .flatMap(Arrays::stream)
                .filter(bingoNumber -> bingoNumber.equals(bingoNumberToMark))
                .findFirst()
                .ifPresent(BingoNumber::mark);

        if (playerType == PlayerType.USER) {
            OutputView.printBingoBoard(this.bingoNumbers);
        }
    }

    public BingoNumber pickBingoNumber() throws IOException {
        return this.bingoNumberPicker.pickBingoNumber();
    }

    public abstract boolean isBingo();

    public BingoNumber[][] getBingoNumbers() {
        return this.bingoNumbers;
    }

    public PlayerType getPlayerType() {
        return this.playerType;
    }
}
