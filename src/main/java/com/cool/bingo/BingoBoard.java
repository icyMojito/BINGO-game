package com.cool.bingo;

public class BingoBoard {
    private final BingoSize bingoSize;
    private BingoNumbers bingoNumbers;

    public BingoBoard(BingoSize bingoSize) {
        this.bingoSize = bingoSize;
        this.bingoNumbers = BingoNumbers.empty();
    }

    public void addNumbers(BingoNumbers bingoNumbers) {
        BingoNumbers newBingoNumbers = this.bingoNumbers.add(bingoNumbers);
        if (this.bingoNumbers.isSmallerThan(bingoSize)) {
            this.bingoNumbers = newBingoNumbers;
        }
    }

    public boolean hasNeedMoreNumbers() {
        return this.bingoNumbers.isSmallerThan(this.bingoSize);
    }

    public BingoNumbers getBingoNumbers() {
        return bingoNumbers;
    }
}
