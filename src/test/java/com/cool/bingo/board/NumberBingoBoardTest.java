package com.cool.bingo.board;

import com.cool.bingo.BingoSize;
import com.cool.bingo.BingoType;
import com.cool.bingo.PlayerType;
import com.cool.bingo.number.BingoNumber;
import com.cool.bingo.number.BingoNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberBingoBoardTest {
    private BingoSize bingoSize;
    private BingoType bingoType;

    @BeforeEach
    void setUp() {
        this.bingoSize = BingoSize.from("3");
        this.bingoType = BingoType.NUMBER;
    }

    @DisplayName("사용자가 만든 빙고판이 숫자빙고 조건(빙고판 줄수만큼 빙고줄을 만듦)을 만족했을 때 빙고임을 알린다.")
    @Test
    void isBingoByUserTest() {
        PlayerType playerType = PlayerType.USER;
        BingoNumbers bingoNumbers = BingoNumbers.of("1,2,3,4,5,6,7,8,9", bingoSize);
        NumberBingoBoard numberBingoBoard = NumberBingoBoard.of(this.bingoSize, this.bingoType, playerType,
                                                                bingoNumbers);
        numberBingoBoard.mark(BingoNumber.of("1", this.bingoSize));
        numberBingoBoard.mark(BingoNumber.of("2", this.bingoSize));
        numberBingoBoard.mark(BingoNumber.of("3", this.bingoSize));
        numberBingoBoard.mark(BingoNumber.of("5", this.bingoSize));
        numberBingoBoard.mark(BingoNumber.of("6", this.bingoSize));
        numberBingoBoard.mark(BingoNumber.of("9", this.bingoSize));

        assertThat(numberBingoBoard.isBingo()).isTrue();
    }

    @DisplayName("컴퓨터가 만든 빙고판이 숫자빙고 조건(빙고판 줄수만큼 빙고줄을 만듦)을 만족했을 때 빙고임을 알린다.")
    @Test
    void isBingoByComputerTest() throws IOException {
        PlayerType playerType = PlayerType.COMPUTER;
        BingoNumbers bingoNumbers = BingoNumbers.of("1,2,3,4,5,6,7,8,9", bingoSize);
        NumberBingoBoard numberBingoBoard = NumberBingoBoard.of(this.bingoSize, this.bingoType, playerType,
                                                                bingoNumbers);

        for (int i = 0; i < this.bingoSize.getSize(); i++) {
            BingoNumber bingoNumber = numberBingoBoard.pickBingoNumber();
            numberBingoBoard.mark(bingoNumber);
        }

        assertThat(numberBingoBoard.isBingo()).isTrue();
    }
}
