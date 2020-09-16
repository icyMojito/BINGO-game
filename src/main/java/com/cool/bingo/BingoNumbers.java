package com.cool.bingo;

import com.cool.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class BingoNumbers {
    private static final String NUMBER_DELIMITER = ",";

    private final LinkedHashSet<BingoNumber> numbers;

    private BingoNumbers(LinkedHashSet<BingoNumber> numbers) {
        this.numbers = numbers;
    }

    public static BingoNumbers of(String bingoNumbersValue, BingoSize bingoSize) {
        StringUtils.validateNonNullAndNotEmpty(bingoNumbersValue);

        LinkedHashSet<BingoNumber> numbers = new LinkedHashSet<>();

        StringTokenizer nums = new StringTokenizer(bingoNumbersValue, NUMBER_DELIMITER);
        while (nums.hasMoreTokens()) {
            String number = nums.nextToken().trim();
            try {
                BingoNumber bingoNumber = BingoNumber.of(number, bingoSize);
                numbers.add(bingoNumber);
            } catch (Exception ignored) {
            }
        }

        return new BingoNumbers(numbers);
    }

    public static BingoNumbers empty() {
        return new BingoNumbers(new LinkedHashSet<>());
    }

    public BingoNumbers add(BingoNumbers bingoNumbers) {
        LinkedHashSet<BingoNumber> numbers = new LinkedHashSet<>(this.numbers);
        numbers.addAll(bingoNumbers.numbers);

        return new BingoNumbers(numbers);
    }

    public boolean isSmallerThan(BingoSize bingoSize) {
        return bingoSize.isBiggerThan(this.numbers.size());
    }

    public LinkedHashSet<BingoNumber> getNumbers() {
        return numbers;
    }
}
