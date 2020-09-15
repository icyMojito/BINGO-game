package com.cool.bingo;

import com.cool.exception.InvalidBingoNumberException;
import com.cool.util.StringUtils;

import java.util.Objects;
import java.util.function.Supplier;

public class BingoNumber {
    private static final int MIN_NUMBER = 1;
    private static final String MARKED = "X";

    private String value;

    private BingoNumber(String value) {
        this.value = value;
    }

    public static BingoNumber of(String value, BingoSize bingoSize) {
        StringUtils.validateNonNullAndNotEmpty(value);
        validateNumberFormat(value);
        validateNumberRange(Integer.parseInt(value), bingoSize);

        return new BingoNumber(value);
    }

    private static void validateNumberFormat(String number) {
        Supplier<RuntimeException> exceptionSupplier = () -> new InvalidBingoNumberException(
                "💥 숫자가 아닌 값이 입력되었어요! → " + number);

        StringUtils.validateNumberFormat(exceptionSupplier, number);
    }

    private static void validateNumberRange(int number, BingoSize bingoSize) {
        if (number < MIN_NUMBER || bingoSize.isBiggerThanMaxNumber(number)) {
            throw new InvalidBingoNumberException("💥 입력할 수 없는 범위의 숫자가 있습니다! → " + number);
        }
    }

    public void mark() {
        this.value = MARKED;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BingoNumber that = (BingoNumber) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
