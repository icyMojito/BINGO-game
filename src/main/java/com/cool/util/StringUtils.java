package com.cool.util;

import java.util.Optional;
import java.util.function.Supplier;

public class StringUtils {

    private StringUtils() {
    }

    public static void validateNonNullAndNotEmpty(String value) {
        Optional.ofNullable(value)
                .map(String::trim)
                .filter(v -> !v.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("null 혹은 빈 값이 매개변수로 들어왔습니다!"));
    }

    public static void validateNumberFormat(Supplier<RuntimeException> exceptionSupplier, String value) {
        validateNonNullAndNotEmpty(value);

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c < '0' || '9' < c || (c == '0' && i == 0)) {
                throw exceptionSupplier.get();
            }
        }
    }
}
