package com.cool.bingo.board;

import com.cool.bingo.BingoSize;
import com.cool.bingo.BingoType;
import com.cool.bingo.PlayerType;
import com.cool.bingo.number.BingoNumber;
import com.cool.bingo.number.BingoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BingoBoardTest {

    @DisplayName("지울 빙고숫자(BingoNumber)를 부르면 해당 빙고숫자는 표시된(marked) 상태가 된다.")
    @Test
    void markTest() {
        BingoSize bingoSize = BingoSize.from("3");
        BingoNumbers bingoNumbers = BingoNumbers.of("1,2,3,4,5,6,7,8,9", bingoSize);
        BingoBoard bingoBoard = BingoBoardFactory.createBingoBoard(bingoSize, BingoType.BLACK, PlayerType.USER,
                                                                   bingoNumbers);
        /*  빙고판
            1 2 3
            4 5 6
            7 8 9
        */
        bingoBoard.mark(BingoNumber.of("1", bingoSize));
        BingoNumber[][] bingoNums = bingoBoard.getBingoNumbers();

        assertThat(bingoNums[0][0].isMarked()).isTrue();
        assertThat(bingoNums[0][0].isNumber()).isFalse();
        assertThat(bingoNums[0][1].isMarked()).isFalse();
        assertThat(bingoNums[0][1].isNumber()).isTrue();
    }
}
