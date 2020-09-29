package com.cool.bingo.number;

import com.cool.bingo.BingoSize;
import com.cool.exception.InvalidBingoNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BingoNumberTest {

    @DisplayName("빙고 사이즈에 따라 정해진 범위에 맞는 숫자값으로 BingoNumber 객체가 생성된다.")
    @ParameterizedTest
    @CsvSource(value = {"3,1", "3,18", "4,1", "4,32", "5,1", "5,50", "6,1", "6,72", "7,1", "7,98", "8,1", "8,128"})
    void bingoNumberTest(String bingoSizeValue, String bingoNumberValue) {
        BingoSize bingoSize = BingoSize.from(bingoSizeValue);

        assertThat(BingoNumber.of(bingoNumberValue, bingoSize)).isInstanceOf(BingoNumber.class);
    }

    @DisplayName("숫자가 아닌 값으로 BingoNumber 객체 생성 시 InvalidBingoNumberException이 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"십일", "!", "one", "아무거나줘", "random", "X"})
    void ofNotNumberExceptionTest(String notNumberValue) {
        BingoSize bingoSize = BingoSize.from("3");

        assertThatThrownBy(() -> BingoNumber.of(notNumberValue, bingoSize))
                .isInstanceOf(InvalidBingoNumberException.class);
    }

    @DisplayName("정해진 범위를 벗어난 숫자값으로 BingoNumber 객체 생성 시 InvalidBingoNumberException이 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"3,-1", "3,19", "4,0", "4,33", "5,0", "5,51", "6,-3", "6,75", "7,0", "7,111", "8,-12", "8,129"})
    void ofInvalidNumberExceptionTest(String bingoSizeValue, String bingoNumberValue) {
        BingoSize bingoSize = BingoSize.from(bingoSizeValue);

        assertThatThrownBy(() -> BingoNumber.of(bingoNumberValue, bingoSize))
                .isInstanceOf(InvalidBingoNumberException.class);
    }
}