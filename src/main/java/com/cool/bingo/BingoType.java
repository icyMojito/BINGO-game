package com.cool.bingo;

import com.cool.exception.InvalidBingoTypeException;
import com.cool.util.StringUtils;

import java.util.Arrays;
import java.util.Random;

public enum BingoType {
    BLACK("B"),
    NUMBER("N");

    private static final Random R = new Random();
    private static final String RANDOM = "R";

    private final String code;

    BingoType(String code) {
        this.code = code;
    }

    public static BingoType from(String bingoCode) {
        StringUtils.validateNonNullAndNotEmpty(bingoCode);

        if (RANDOM.equalsIgnoreCase(bingoCode)) {
            boolean isBlack = R.nextBoolean();
            if (isBlack) {
                return BLACK;
            }
            return NUMBER;
        }

        return Arrays.stream(BingoType.values())
                .filter(type -> type.code.equalsIgnoreCase(bingoCode))
                .findFirst()
                .orElseThrow(() -> new InvalidBingoTypeException(bingoCode));
    }
}
