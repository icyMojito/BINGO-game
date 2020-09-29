package com.cool.bingo.numberPicker;

import com.cool.bingo.BingoSize;
import com.cool.bingo.number.BingoNumber;
import com.cool.bingo.number.BingoNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ComputerBingoNumberPickerTest {
    private BingoNumber[][] bingoNumbers;
    /*
     * X 2 3
     * 4 X 6
     * 7 8 9
     * */

    @BeforeEach
    void setUp() {
        BingoSize bingoSize = BingoSize.from("3");
        BingoNumbers bingoNums = BingoNumbers.of("1,2,3,4,5,6,7,8,9", bingoSize);
        this.bingoNumbers = bingoNums.create2dBingoNumbers(bingoSize);
        this.bingoNumbers[0][0].mark();
        this.bingoNumbers[1][1].mark();
    }

    @DisplayName("블랙 빙고 조건 아래 지우지 않은 BingoNumber를 나열한 순서대로 반환한다.")
    @Test
    void pickBingoNumberUnderBlackBingoTest() throws IOException {
        ComputerBlackBingoNumberPicker computerBlackBingoNumberPicker =
                new ComputerBlackBingoNumberPicker(this.bingoNumbers);
        BingoNumber bingoNumber = computerBlackBingoNumberPicker.pickBingoNumber();

        assertThat(bingoNumber.getValue()).isEqualTo("2");

        this.bingoNumbers[0][1].mark();
        bingoNumber = computerBlackBingoNumberPicker.pickBingoNumber();

        assertThat(bingoNumber.getValue()).isEqualTo("3");
    }

    @DisplayName("숫자 빙고 조건 아래 1줄 빙고를 달성할 수 있는 BingoNumber(9)를 반환한다.")
    @Test
    void pickBingoNumberUnderNumberBingoTest() throws IOException {
        ComputerNumberBingoNumberPicker computerNumberBingoNumberPicker =
                new ComputerNumberBingoNumberPicker(this.bingoNumbers);
        BingoNumber bingoNumber = computerNumberBingoNumberPicker.pickBingoNumber();

        assertThat(bingoNumber.getValue()).isEqualTo("9");
    }
}
