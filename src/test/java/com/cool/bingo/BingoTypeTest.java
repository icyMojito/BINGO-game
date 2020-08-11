package com.cool.bingo;

import com.cool.exception.InvalidBingoTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BingoTypeTest {

    @DisplayName("빙고의 종류로 적절한 문자가 입력되면 BingoType 객체가 생성된다.")
    @ParameterizedTest
    @ValueSource(strings = {"R", "B", "N", "r", "b", "n"})
    void succeedToCreateBingoTypeInstance(String playerAnswer) {
        assertThat(BingoType.from(playerAnswer)).isInstanceOf(BingoType.class);
    }

    @DisplayName("빙고의 종류로 알파벳이 아닌 값이 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"블랙", "1", "?", "2~", "n빙고"})
    void failToCreateBingoTypeInstanceByNotEngCharacter(String playerAnswer) {
        assertThatThrownBy(() -> BingoType.from(playerAnswer))
            .isInstanceOf(InvalidBingoTypeException.class);
    }

    @DisplayName("빙고의 종류로 허용된 알파벳이 아닌 값이 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "C", "r~@", "m", "n?", "o", "Z"})
    void failToCreateBingoTypeInstanceByNotAllowedEngCharacter(String playerAnswer) {
        assertThatThrownBy(() -> BingoType.from(playerAnswer))
            .isInstanceOf(InvalidBingoTypeException.class);
    }
}
