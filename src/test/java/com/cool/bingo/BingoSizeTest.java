package com.cool.bingo;

import com.cool.exception.InvalidBingoSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BingoSizeTest {

    @DisplayName("빙고판의 크기로 적절한 숫자가 입력되면 BingoSize 객체가 생성된다.")
    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "5", "6", "7", "8"})
    void succeedToCreateBingoSizeInstance(String playerAnswer) {
        assertThat(BingoSize.from(playerAnswer)).isInstanceOf(BingoSize.class);
    }

    @DisplayName("빙고판의 크기로 숫자가 아닌 값이 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"칠", "?", "one", "아무거나", "I"})
    void failToCreateBingoSizeInstanceByNotNumber(String playerAnswer) {
        assertThatThrownBy(() -> BingoSize.from(playerAnswer))
            .isInstanceOf(InvalidBingoSizeException.class);
    }

    @DisplayName("빙고판의 크기로 허용된 숫자가 아닌 값이 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "-9", "9", "10", "0"})
    void failToCreateBingoSizeInstanceByNotAllowedNumber(String playerAnswer) {
        assertThatThrownBy(() -> BingoSize.from(playerAnswer))
            .isInstanceOf(InvalidBingoSizeException.class);
    }
}
