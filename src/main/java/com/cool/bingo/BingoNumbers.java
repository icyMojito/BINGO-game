package com.cool.bingo;

import com.cool.exception.InvalidBingoNumberException;
import com.cool.util.StringUtils;

import java.util.*;

public class BingoNumbers {
    private static final String NUMBER_DELIMITER = ",";

    private final LinkedHashSet<BingoNumber> numbers;

    public BingoNumbers(LinkedHashSet<BingoNumber> numbers) {
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

    public static BingoNumbers createRandomBingoNumbers(BingoSize bingoSize) {
        LinkedHashSet<BingoNumber> bingoNums = new LinkedHashSet<>();

        while (bingoSize.isBiggerThan(bingoNums.size())) {
            int number = bingoSize.createRandomNumber();
            BingoNumber bingoNumber = BingoNumber.from(number);
            bingoNums.add(bingoNumber);
        }

        return new BingoNumbers(bingoNums);
    }

    public BingoNumbers add(BingoNumbers bingoNumbers) {
        LinkedHashSet<BingoNumber> numbers = new LinkedHashSet<>(this.numbers);
        numbers.addAll(bingoNumbers.numbers);

        return new BingoNumbers(numbers);
    }

    public void markBingoNumber(BingoNumber bingoNumberToRemove) {
        this.numbers.stream()
                .filter(bingoNumber -> bingoNumber.equals(bingoNumberToRemove))
                .findFirst()
                .ifPresent(BingoNumber::mark);
    }

    public BingoNumber getRandomBingoNumber() {
        return this.numbers.stream()
                .filter(BingoNumber::isNumber)
                .findFirst()
                .orElseThrow(() -> new InvalidBingoNumberException("더 이상 남아있는 숫자가 없습니다!"));
    }

    public boolean isAllRemoved() {
        return this.numbers.stream()
                .allMatch(BingoNumber::isRemoved);
    }

    public int calculateSizeGap(BingoSize bingoSize) {
        return this.numbers.size() - bingoSize.getSize();
    }

    public BingoNumbers reduce(BingoSize bingoSize) {
        ArrayList<BingoNumber> numbers = new ArrayList<>(this.numbers);

        while (bingoSize.isSmallerThan(numbers.size())) {
            numbers.remove(numbers.size() - 1);
        }

        LinkedHashSet<BingoNumber> newBingoNumbers = new LinkedHashSet<>(numbers);

        return new BingoNumbers(newBingoNumbers);
    }

    public Set<BingoNumber> getNumbers() {
        return Collections.unmodifiableSet(numbers);
    }
}
