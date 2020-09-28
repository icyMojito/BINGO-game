package com.cool.bingo;

import com.cool.bingo.number.BingoNumber;
import com.cool.bingo.number.BingoNumbers;
import com.cool.exception.InvalidBingoSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BingoSizeTest {

    @DisplayName("조건에 맞는 숫자이면 BingoSize 객체가 생성된다.")
    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "5", "6", "7", "8"})
    void fromTest(String validNumber) {
        assertThat(BingoSize.from(validNumber)).isInstanceOf(BingoSize.class);
    }

    @DisplayName("숫자가 아닌 값으로 BingoSize 객체 생성 시 InvalidBingoLineCountException이 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"칠", "?", "one", "아무거나", "I"})
    void fromNotNumberExceptionTest(String notNumber) {
        assertThatThrownBy(() -> BingoSize.from(notNumber))
                .isInstanceOf(InvalidBingoSizeException.class);
    }

    @DisplayName("정해진 범위를 벗어난 숫자값으로 BingoSize 객체 생성 시 InvalidBingoLineCountException이 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "-9", "9", "10", "0"})
    void fromInvalidNumberExceptionTest(String invalidNumber) {
        assertThatThrownBy(() -> BingoSize.from(invalidNumber))
                .isInstanceOf(InvalidBingoSizeException.class);
    }

    @DisplayName("빙고숫자묶음(BingoNumbers)을 BingoSize를 이용해 2차원 배열로 바꿀 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"3:1,2,3,4,5,6,7,8,9", "4:1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16",
            "5:1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25"}, delimiter = ':')
    void create2dBingoNumbersTest(String bingoSizeNumber, String bingoNumbersValue) {
        BingoSize bingoSize = BingoSize.from(bingoSizeNumber);
        BingoNumbers bingoNumbers = BingoNumbers.of(bingoNumbersValue, bingoSize);

        BingoNumber[][] numbers = bingoSize.create2dBingoNumbers(bingoNumbers.getIterator());

        int bingoSizeValue = bingoSize.getValue();
        assertThat(numbers).hasSize(bingoSizeValue);
        for (int i = 0; i < bingoSizeValue; i++) {
            assertThat(numbers[i]).hasSize(bingoSizeValue);
        }
    }
}
