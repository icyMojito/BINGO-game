package com.cool.bingo.board;

import com.cool.bingo.BingoSize;
import com.cool.bingo.BingoType;
import com.cool.bingo.PlayerType;
import com.cool.bingo.number.BingoNumber;
import com.cool.bingo.number.BingoNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

class BlackBingoBoardTest {
    private BingoSize bingoSize;
    private BingoType bingoType;

    @BeforeEach
    void setUp() {
        this.bingoSize = BingoSize.from("3");
        this.bingoType = BingoType.BLACK;
    }

    @DisplayName("사용자가 만든 빙고판이 블랙빙고 조건(모든 빙고숫자가 지워짐)을 만족했을 때 빙고임을 알린다.")
    @Test
    void isBingoByUserTest() {
        PlayerType playerType = PlayerType.USER;
        BingoNumbers bingoNumbers = BingoNumbers.of("1,2,3,4,5,6,7,8,9", bingoSize);
        BlackBingoBoard blackBingoBoard = BlackBingoBoard.of(this.bingoSize, this.bingoType, playerType, bingoNumbers);

        Iterator<BingoNumber> userBingoNums = bingoNumbers.getIterator();
        userBingoNums.forEachRemaining(blackBingoBoard::mark);

        assertThat(blackBingoBoard.isBingo()).isTrue();
    }

    @DisplayName("컴퓨터가 만든 빙고판이 블랙빙고 조건(모든 빙고숫자가 지워짐)을 만족했을 때 빙고임을 알린다.")
    @Test
    void isBingoByComputerTest() {
        PlayerType playerType = PlayerType.COMPUTER;
        BingoNumbers bingoNumbers = BingoNumbers.createRandomBingoNumbers(this.bingoSize);
        BlackBingoBoard blackBingoBoard = BlackBingoBoard.of(this.bingoSize, this.bingoType, playerType, bingoNumbers);

        Iterator<BingoNumber> userBingoNums = bingoNumbers.getIterator();
        userBingoNums.forEachRemaining(blackBingoBoard::mark);

        assertThat(blackBingoBoard.isBingo()).isTrue();
    }
}
