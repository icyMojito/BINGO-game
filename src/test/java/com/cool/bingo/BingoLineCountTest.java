package com.cool.bingo;

import com.cool.exception.InvalidBingoLineCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BingoLineCountTest {

    @DisplayName("빙고판의 크기로 적절한 숫자가 입력되면 BingoSize 객체가 생성된다.")
    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "5", "6", "7", "8"})
    void succeedToCreateBingoLineCountInstance(String playerAnswer) {
        assertThat(BingoLineCount.from(playerAnswer)).isInstanceOf(BingoLineCount.class);
    }

    @DisplayName("빙고판의 크기로 숫자가 아닌 값이 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"칠", "?", "one", "아무거나", "I"})
    void failToCreateBingoLineCountInstanceByNotNumber(String playerAnswer) {
        assertThatThrownBy(() -> BingoLineCount.from(playerAnswer))
            .isInstanceOf(InvalidBingoLineCountException.class);
    }

    @DisplayName("빙고판의 크기로 허용된 숫자가 아닌 값이 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "-9", "9", "10", "0"})
    void failToCreateBingoLineCountInstanceByNotAllowedNumber(String playerAnswer) {
        assertThatThrownBy(() -> BingoLineCount.from(playerAnswer))
            .isInstanceOf(InvalidBingoLineCountException.class);
    }

    @DisplayName("빙고판의 크기가 정상적으로 정해졌을 때, 빙고 숫자의 전체 개수를 정확하게 계산한다.")
    @ParameterizedTest
    @CsvSource({"3,9","4,16","5,25","6,36","7,49","8,64"})
    void computeTotalBingoNumberCountTest(String input, int expected) {
        BingoLineCount bingoLineCount = BingoLineCount.from(input);

        assertThat(bingoLineCount.computeTotalBingoNumbersCount()).isEqualTo(expected);
    }

    @DisplayName("빙고판의 크기가 정상적으로 정해졌을 때, 규칙에 따른 빙고 숫자의 최대값을 정확하게 계산한다.")
    @ParameterizedTest
    @CsvSource({"3,18","4,32","5,50","6,72","7,98","8,128"})
    void computeBingoMaxNumberTest(String input, int expected) {
        BingoLineCount bingoLineCount = BingoLineCount.from(input);

        assertThat(bingoLineCount.computeBingoMaxNumber()).isEqualTo(expected);
    }
}
