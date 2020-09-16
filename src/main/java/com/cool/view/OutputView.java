package com.cool.view;

import com.cool.bingo.BingoNumber;
import com.cool.bingo.BingoNumbers;
import com.cool.bingo.BingoSize;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SPACE = " ";
    private static final String DOUBLE_SPACE = "  ";
    private static final String TRIPLE_SPACE = "   ";
    public static final String NUMBER_DELIMITER = ",";

    public static void printRequestForBingoSize() throws IOException {
        bufferedWriter.write("👼🏼 " + ViewColor.MAGENTA_BOLD.fillText("빙고판의 크기") + "를 입력해 주세요!");
        bufferedWriter.newLine();
        bufferedWriter.write("⚠️ 빙고판의 크기는 " + ViewColor.YELLOW_UNDERLINED.fillText("3X3부터 8X8까지")
                                     + " 지정할 수 있으며, " + ViewColor.RED_BOLD.fillText("숫자만") + " 입력 가능해요.");
        bufferedWriter.newLine();
        bufferedWriter.write("   예를 들어 4X4로 하고 싶으면 4만 적어주세요.");
        bufferedWriter.newLine();
        bufferedWriter.write("➡️ ");

        bufferedWriter.flush();
    }

    public static void printRequestForBingoType() throws IOException {
        bufferedWriter.write("👼🏼 " + ViewColor.MAGENTA_BOLD.fillText("빙고의 종류") + "를 입력해 주세요!");
        bufferedWriter.newLine();
        bufferedWriter.write("⚠️ 빙고의 종류는 " + ViewColor.BLACK_UNDERLINED.fillText("블랙빙고")
                                     + " 혹은 "
                                     + ViewColor.GREEN_UNDERLINED.fillText("N빙고")
                                     + "로 지정할 수 있으며, "
                                     + ViewColor.BLUE_UNDERLINED.fillText("랜덤")
                                     + " 선택도 가능합니다. "
                                     + ViewColor.BLACK_BOLD.fillText("블랙빙고는 B(b)")
                                     + ","
                                     + ViewColor.GREEN_BOLD.fillText("N빙고는 N(n)")
                                     + ","
                                     + ViewColor.BLUE_BOLD.fillText("랜덤 지정은 R(r)")
                                     + "을 입력해 주세요.");
        bufferedWriter.newLine();
        bufferedWriter.write("➡️ ");

        bufferedWriter.flush();
    }

    public static void printRequestForBingoNumber(int bingoLineCount) throws IOException {
        int totalBingoNumbersCount = (int) Math.pow(bingoLineCount, 2);
        int maxNumber = (int) Math.pow(bingoLineCount, 2) * 2;

        bufferedWriter.write("👼🏼 " + ViewColor.MAGENTA_BOLD.fillText("빙고에 넣을 숫자 " + totalBingoNumbersCount + "개")
                                     + "를 입력해 주세요! "
                                     + ViewColor.MAGENTA_BOLD.fillText("한 번에 1개부터 최대 " + totalBingoNumbersCount +
                                                                               "개까지 ")
                                     + "적을 수 있어요.");
        bufferedWriter.newLine();
        bufferedWriter.write("숫자 사이는 \"" + ViewColor.MAGENTA.fillText(NUMBER_DELIMITER) + "\"로 구별이 필요해요. 꼭 넣어주세요~");
        bufferedWriter.newLine();
        bufferedWriter.write("⚠️ 빙고의 숫자는 " + ViewColor.YELLOW_UNDERLINED.fillText("1부터 " + maxNumber + "까지")
                                     + " 입력할 수 있으며, " + ViewColor.BLACK_BOLD.fillText("숫자만 ")
                                     + ViewColor.BLUE_BOLD.fillText("중복 없이") + " 입력 가능해요.");
        bufferedWriter.newLine();

        bufferedWriter.flush();
    }

    public static void printRequestForNumberInput() throws IOException {
        bufferedWriter.write("➡️ ");
        bufferedWriter.flush();
    }

    public static void printBingoBoard(BingoSize bingoSize, BingoNumbers bingoNumbers) throws IOException {
        int bingoSizeValue = bingoSize.getSize();
        int columnCount = (int) Math.sqrt(bingoSizeValue);
        LinkedHashSet<BingoNumber> numbers = bingoNumbers.getNumbers();
        StringBuilder bingoBoard = new StringBuilder();

        List<String> nums = numbers.stream()
                .map(BingoNumber::getValue)
                .collect(Collectors.toList());

        int columnIndex = 0;
        for (int i = 0; i < bingoSizeValue; i++) {
            String num = i < nums.size() ? nums.get(i) : ".";
            String space = num.length() == 1 ? TRIPLE_SPACE : num.length() == 2 ? DOUBLE_SPACE : SPACE;
            bingoBoard.append(num)
                    .append(space);
            columnIndex++;
            if (columnIndex == columnCount) {
                bingoBoard.append(NEW_LINE);
                columnIndex = 0;
            }
        }

        bufferedWriter.write(bingoBoard.toString());
        bufferedWriter.flush();
    }

    public static void printExceptionMessage(Exception e) throws IOException {
        bufferedWriter.write("💥😱 ");
        bufferedWriter.write(e.getMessage());
        bufferedWriter.write(NEW_LINE);
        bufferedWriter.write(NEW_LINE);
        bufferedWriter.flush();
    }

    public static void close() throws IOException {
        bufferedWriter.close();
    }
}
