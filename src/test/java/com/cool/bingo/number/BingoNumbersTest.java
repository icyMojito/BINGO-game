package com.cool.bingo.number;

import com.cool.bingo.BingoSize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;

import static org.assertj.core.api.Assertions.assertThat;

class BingoNumbersTest {

    @DisplayName("빙고 사이즈에 따라 정해진 범위에 맞는 복수의 숫자값으로 BingoNumbers 객체가 생성된다.")
    @ParameterizedTest
    @CsvSource(value = {"1;3", "1,2,3;4", "1,2,3,4,5,6,7;5", "1,2,4,8,11,16,25,29,32;6", "88,77,66,55,44,33,22,11,1;7"},
            delimiter = ';')
    void bingoNumbersTest(String bingoNumbersValue, String bingoSizeValue) {
        BingoSize bingoSize = BingoSize.from(bingoSizeValue);

        assertThat(BingoNumbers.of(bingoNumbersValue, bingoSize)).isInstanceOf(BingoNumbers.class);
    }

    @DisplayName("숫자가 아닌 값이 포함된 복수의 숫자값으로도 BingoNumbers 객체가 생성된다.")
    @ParameterizedTest
    @CsvSource(value = {"일이삼,사!;3", "10,eleven,12,13;4", "i1,v,vii,25,50;5", "아무거나,주세요,숫자로,?;6"}, delimiter = ';')
    void bingoNumbersIncludingNotNumberTest(String bingoNumbersValue, String bingoSizeValue) {
        BingoSize bingoSize = BingoSize.from(bingoSizeValue);

        assertThat(BingoNumbers.of(bingoNumbersValue, bingoSize)).isInstanceOf(BingoNumbers.class);
    }

    @DisplayName("빙고 사이즈에 따라 정해진 범위를 벗어난 숫자값이 포함된 복수의 숫자값으로도 BingoNumbers 객체가 생성된다.")
    @ParameterizedTest
    @CsvSource(value = {"-11,-8,0;3", "1,2,3,4,33,35;4", "50,51,52,114;5", "-1,0,1,72,73;6"}, delimiter = ';')
    void bingoNumbersIncludingInvalidNumberTest(String bingoNumbersValue, String bingoSizeValue) {
        BingoSize bingoSize = BingoSize.from(bingoSizeValue);

        assertThat(BingoNumbers.of(bingoNumbersValue, bingoSize)).isInstanceOf(BingoNumbers.class);
    }

    @DisplayName("빙고 사이즈에 따라 빙고판에 들어갈 수 있는 최대 개수의 무작위 숫자값들로 이루어진 BingoNumbers 객체가 생성된다.")
    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "5", "6", "7", "8"})
    void createRandomBingoNumbersTest(String bingoSizeValue) {
        BingoSize bingoSize = BingoSize.from(bingoSizeValue);
        BingoNumbers randomBingoNumbers = BingoNumbers.createRandomBingoNumbers(bingoSize);

        assertThat(randomBingoNumbers.getSize()).isEqualTo(bingoSize.getSize());
    }

    @DisplayName("빙고숫자값들을 모으면 정상적으로 합쳐진다.")
    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4;10,14", "7;20", "11,12;13,14,15,16,17,18,19,20"}, delimiter = ';')
    void addBingoNumbersTest(String bingoNumbersValue1, String bingoNumbersValue2) {
        BingoSize bingoSize = BingoSize.from("3");
        BingoNumbers bingoNumbers1 = BingoNumbers.of(bingoNumbersValue1, bingoSize);
        BingoNumbers bingoNumbers2 = BingoNumbers.of(bingoNumbersValue2, bingoSize);
        BingoNumbers result = bingoNumbers1.add(bingoNumbers2);

        assertThat(result.getSize()).isEqualTo(bingoNumbers1.getSize() + bingoNumbers2.getSize());
    }

    @DisplayName("빙고숫자값들을 모으면 중복된 숫자값은 한 번만 들어가고 정상적으로 합쳐진다.")
    @ParameterizedTest
    @CsvSource(value = {"17;17,18", "1,2,3,4;3,4,5,6", "10,11,12;10", "3,4,5,6,7;7,8,9,10"}, delimiter = ';')
    void addDuplicatedBingoNumbersTest(String bingoNumbersValue1, String bingoNumbersValue2) {
        BingoSize bingoSize = BingoSize.from("3");
        BingoNumbers bingoNumbers1 = BingoNumbers.of(bingoNumbersValue1, bingoSize);
        BingoNumbers bingoNumbers2 = BingoNumbers.of(bingoNumbersValue2, bingoSize);

        Set<String> notDuplicatedBingoNumbersValues = gatherBingoNumbersValues(bingoNumbersValue1, bingoNumbersValue2);
        BingoNumbers result = bingoNumbers1.add(bingoNumbers2);

        assertThat(result.getSize()).isEqualTo(notDuplicatedBingoNumbersValues.size());
    }

    private Set<String> gatherBingoNumbersValues(String bingoNumbersValue1, String bingoNumbersValue2) {
        StringJoiner sj = new StringJoiner(",");
        sj.add(bingoNumbersValue1);
        sj.add(bingoNumbersValue2);

        Set<String> notDuplicatedBingoNumbers = new HashSet<>();

        StringTokenizer st = new StringTokenizer(sj.toString(), ",");
        while (st.hasMoreTokens()) {
            notDuplicatedBingoNumbers.add(st.nextToken());
        }

        return notDuplicatedBingoNumbers;
    }

    @DisplayName("빙고판에 들어갈 수 있는 최대 개수보다 BingoNumbers 객체에 담긴 숫자값들이 많으면, 최대 개수에 맞게 숫자값의 개수를 줄인다.")
    @Test
    void reduceTest() {
        BingoSize bingoSize = BingoSize.from("3");
        BingoNumbers bingoNumbers = BingoNumbers.of("1,2,3,4,5,6,7,8,9,10,11", bingoSize);
        BingoNumbers resultBingoNumbers = bingoNumbers.reduce(bingoSize);

        assertThat(resultBingoNumbers.getSize()).isEqualTo(bingoSize.getSize());
    }
}
