package com.cool.bingo.util;

import com.cool.util.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringUtilsTest {

    @DisplayName("null이나 빈 값이 매개변수로 들어오면 Exception이 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void NullAndEmptyExceptionTest(String value) {
        assertThatThrownBy(() -> StringUtils.validateNonNullAndNotEmpty(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null 혹은 빈 값이 매개변수로 들어왔습니다!");
    }

    @DisplayName("정수가 아닌 값이 매개변수로 들어오면 매개변수로 전달된 Exception이 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"10삼", "xiii", "삼십일", "9!", "one", "-4"})
    void validateNumberFormatExceptionTest(String value) {
        Supplier<RuntimeException> exceptionSupplier = () -> new IllegalArgumentException("정수가 아닌 숫자값입니다!");

        assertThatThrownBy(() -> StringUtils.validateNumberFormat(exceptionSupplier, value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정수가 아닌 숫자값입니다!");
    }
}
