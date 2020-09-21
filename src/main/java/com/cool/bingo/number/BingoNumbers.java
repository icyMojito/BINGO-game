package com.cool.bingo.number;

import com.cool.bingo.BingoSize;
import com.cool.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
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

        StringTokenizer st = new StringTokenizer(bingoNumbersValue, NUMBER_DELIMITER);
        while (st.hasMoreTokens()) {
            String number = st.nextToken().trim();
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
        LinkedHashSet<BingoNumber> numbers = new LinkedHashSet<>();

        while (bingoSize.isBiggerThan(numbers.size())) {
            int number = bingoSize.createRandomNumber();
            BingoNumber bingoNumber = BingoNumber.from(number);
            numbers.add(bingoNumber);
        }

        return new BingoNumbers(numbers);
    }

    public BingoNumbers add(BingoNumbers bingoNumbers) {
        LinkedHashSet<BingoNumber> nums = new LinkedHashSet<>(this.numbers);
        nums.addAll(bingoNumbers.numbers);

        return new BingoNumbers(nums);
    }

    public BingoNumbers reduce(BingoSize bingoSize) {
        ArrayList<BingoNumber> nums = new ArrayList<>(this.numbers);

        while (bingoSize.isSmallerThan(nums.size())) {
            nums.remove(nums.size() - 1);
        }

        LinkedHashSet<BingoNumber> newBingoNumbers = new LinkedHashSet<>(nums);

        return new BingoNumbers(newBingoNumbers);
    }

    public BingoNumber[][] create2dBingoNumbers(BingoSize bingoSize) {
        return bingoSize.create2dBingoNumbers(getIterator());
    }

    public Iterator<BingoNumber> getIterator() {
        return this.numbers.iterator();
    }

    public int getSize() {
        return this.numbers.size();
    }
}
