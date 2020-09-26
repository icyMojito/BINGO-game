package com.cool.view;

import com.cool.bingo.BingoSize;
import com.cool.bingo.BingoType;
import com.cool.bingo.PlayerType;
import com.cool.bingo.board.BingoBoard;
import com.cool.bingo.number.BingoNumber;
import com.cool.bingo.number.BingoNumbers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class OutputView {
    private static final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SPACE = " ";
    private static final String DOUBLE_SPACE = "  ";
    private static final String TRIPLE_SPACE = "   ";
    public static final String NUMBER_DELIMITER = ",";

    public static void printRequestForBingoSize() throws IOException {
        bufferedWriter.newLine();
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
        bufferedWriter.newLine();
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

    public static void printRequestForBingoNumber(BingoSize bingoSize) throws IOException {
        int totalBingoNumbersCount = bingoSize.getSize();
        int maxNumber = totalBingoNumbersCount * 2;

        bufferedWriter.newLine();
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

    public static void printBingoNumbers(BingoNumbers bingoNumbers, BingoSize bingoSize) throws IOException {
        int size = (int) Math.sqrt(bingoSize.getSize());
        BingoNumber[][] numbers = new BingoNumber[size][size];
        Iterator<BingoNumber> iterator = bingoNumbers.getIterator();

        for (int row = 0; row < numbers.length; row++) {
            for (int col = 0; col < numbers[row].length; col++) {
                try {
                    numbers[row][col] = iterator.next();
                } catch (NoSuchElementException e) {
                    break;
                }
            }
        }

        printBingoBoard(numbers);
    }

    public static void printBingoBoard(BingoNumber[][] bingoNumbers) throws IOException {
        StringBuilder bingoBoard = new StringBuilder();

        for (int row = 0; row < bingoNumbers.length; row++) {
            bingoBoard.append(" ");
            for (int col = 0; col < bingoNumbers[row].length; col++) {
                BingoNumber bingoNumber = bingoNumbers[row][col];
                String value = Objects.nonNull(bingoNumber) ? bingoNumber.getValue() : ".";
                String space = value.length() == 1 ? TRIPLE_SPACE : value.length() == 2 ? DOUBLE_SPACE : SPACE;
                bingoBoard.append(value)
                        .append(space);
            }
            bingoBoard.append(NEW_LINE);
        }

        bufferedWriter.write("----빙고판----");
        bufferedWriter.newLine();
        bufferedWriter.write(bingoBoard.toString());
        bufferedWriter.write("------------");
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public static void printWinBingoBoard(BingoNumber[][] bingoNumbers, PlayerType playerType) throws IOException {
        String winner = playerType == PlayerType.USER ? "사용자" : "컴퓨터";

        bufferedWriter.write("💘 " + winner + " BINGO 💘");
        bufferedWriter.newLine();

        printBingoBoard(bingoNumbers);
    }

    public static void printExceptionMessage(Exception e) throws IOException {
        bufferedWriter.write("💥😱 ");
        bufferedWriter.write(e.getMessage());
        bufferedWriter.write(NEW_LINE);
        bufferedWriter.write(NEW_LINE);
        bufferedWriter.flush();
    }

    public static void printRequestToMarkBingoNumber(BingoType bingoType, BingoSize bingoSize) throws IOException {
        int totalBingoNumbersCount = bingoSize.getSize();
        int maxNumber = totalBingoNumbersCount * 2;
        int bingoSizeValue = bingoSize.getValue();
        String bingoTypeCode = bingoType.getCode().equals("B") ? "블랙 빙고" : bingoSizeValue + "줄 빙고";

        bufferedWriter.newLine();
        bufferedWriter.write("👼🏼 " + ViewColor.MAGENTA_BOLD.fillText("빙고판에서 지울 숫자")
                                     + ViewColor.RED_BOLD.fillText(" 1개")
                                     + "를 입력해 주세요! "
                                     + ViewColor.GREEN_BOLD.fillText(bingoTypeCode) + "가 승리 조건이에요.");
        bufferedWriter.newLine();
        bufferedWriter.write("⚠️ 빙고의 숫자는 " + ViewColor.YELLOW_UNDERLINED.fillText("1부터 " + maxNumber + "까지")
                                     + " 입력할 수 있으며, " + ViewColor.BLACK_BOLD.fillText("숫자만")
                                     + " 입력 가능해요.");
        bufferedWriter.newLine();
        bufferedWriter.write("➡️ ");

        bufferedWriter.flush();
    }

    public static void printNoticeForInvalidBingoNumber(String message) throws IOException {
        bufferedWriter.write("👼🏼 숫자를 입력하지 않거나 잘못된 숫자를 넣었네요! : " + message);
        bufferedWriter.newLine();

        bufferedWriter.flush();
    }

    public static void printComputerBingoNumberToMark(BingoNumber bingoNumber) throws IOException {
        bufferedWriter.newLine();
        bufferedWriter.write("💻 " + ViewColor.BLUE_UNDERLINED.fillText("컴퓨터") + "가 숫자를 불렀네요! ➡️ " + bingoNumber.getValue());
        bufferedWriter.newLine();

        bufferedWriter.flush();
    }

    public static void printGameResult(BingoBoard userBingoBoard) throws IOException {
        String gameResultMessage = userBingoBoard.isBingo() ?
                "축하합니다🎉 승리하셨어요!!!" : "안타깝게도 컴퓨터가 이겼네요..☠️";

        bufferedWriter.newLine();
        bufferedWriter.write("👼🏼 " + gameResultMessage);

        bufferedWriter.flush();
    }

    public static void close() throws IOException {
        bufferedWriter.close();
    }
}
