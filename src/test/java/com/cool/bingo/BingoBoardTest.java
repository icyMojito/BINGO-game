package com.cool.bingo;

import com.cool.exception.InvalidBingoNumberException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BingoBoardTest {
    private BingoBoard bingoBoard;

    @BeforeAll
    void setUp() {
        BingoLineCount bingoLineCount = BingoLineCount.from("8");

        this.bingoBoard = BingoBoard.of(bingoLineCount);
    }

    @DisplayName("빙고판에 숫자가 아닌 값을 넣을 때 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"일", "삼", "아무거나", "seven", "V", "ten", "?"})
    void fillNotNumberThenThrowException(String input) {
        int tempOrder = 0;

        assertThatThrownBy(() -> this.bingoBoard.fillNumber(tempOrder, input))
                .isInstanceOf(InvalidBingoNumberException.class);
    }

    @DisplayName("빙고판에 정해진 범위의 숫자가 아닌 값을 넣을 때 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "129", "345", "1_000"})
    void fillOutOfRangeNumberThenThrowException(String input) {
        int tempOrder = 0;

        assertThatThrownBy(() -> this.bingoBoard.fillNumber(tempOrder, input))
                .isInstanceOf(InvalidBingoNumberException.class);
    }

    @DisplayName("빙고판에 이미 입력된 숫자 값을 넣을 때 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "32", "64"})
    void fillDuplicatedNumberThenThrowException(String input) {
        int firstOrder = 0;
        int secondOrder = 1;
        this.bingoBoard.fillNumber(firstOrder, input);

        assertThatThrownBy(() -> this.bingoBoard.fillNumber(secondOrder, input))
                .isInstanceOf(InvalidBingoNumberException.class);
    }
}
