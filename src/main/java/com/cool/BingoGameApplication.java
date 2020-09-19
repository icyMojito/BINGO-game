package com.cool;

import com.cool.bingo.BingoNumber;
import com.cool.bingo.BingoNumbers;
import com.cool.bingo.BingoSize;
import com.cool.bingo.BingoType;
import com.cool.bingo.board.BingoBoard;
import com.cool.bingo.board.ComputerBingoBoard;
import com.cool.bingo.board.UserBingoBoard;
import com.cool.exception.InvalidBingoNumberException;
import com.cool.view.InputView;
import com.cool.view.OutputView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BooleanSupplier;

public class BingoGameApplication {
    public static void main(String[] args) throws IOException {
        BingoSize bingoSize = createBingoSize();
        BingoType bingoType = createBingoType();

        UserBingoBoard userBingoBoard = new UserBingoBoard(bingoSize);
        addNumbersToUserBingoBoard(userBingoBoard, bingoSize);

        ComputerBingoBoard computerBingoBoard = new ComputerBingoBoard(bingoSize);

        removeNumbersInBingoBoard(bingoSize, userBingoBoard, computerBingoBoard);

        OutputView.printGameResult(userBingoBoard);
    }

    private static BingoSize createBingoSize() throws IOException {
        OutputView.printRequestForBingoSize();
        String bingoSize = InputView.requestPlayerInput();
        try {
            return BingoSize.from(bingoSize);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e);
            return createBingoSize();
        }
    }

    private static BingoType createBingoType() throws IOException {
        OutputView.printRequestForBingoType();
        String bingoType = InputView.requestPlayerInput();
        try {
            return BingoType.from(bingoType);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e);
            return createBingoType();
        }
    }

    private static void addNumbersToUserBingoBoard(UserBingoBoard userBingoBoard, BingoSize bingoSize) throws IOException {
        OutputView.printRequestForBingoNumber(bingoSize);

        while (userBingoBoard.hasNeedMoreNumbers()) {
            OutputView.printRequestForNumberInput();
            String bingoNumbersValue = InputView.requestPlayerInput();
            try {
                BingoNumbers bingoNumbers = BingoNumbers.of(bingoNumbersValue, bingoSize);
                userBingoBoard.addNumbers(bingoNumbers);
                OutputView.printBingoBoard(bingoSize, userBingoBoard.getBingoNumbers());
            } catch (InvalidBingoNumberException e) {
                OutputView.printExceptionMessage(e);
                OutputView.printRequestForBingoNumber(bingoSize);
            }
        }
    }

    private static void removeNumbersInBingoBoard(BingoSize bingoSize,
                                                  UserBingoBoard userBingoBoard,
                                                  ComputerBingoBoard computerBingoBoard) throws IOException {
        List<BingoBoard> boards = Arrays.asList(userBingoBoard, computerBingoBoard);
        BooleanSupplier hasAnyBlackBingo = () -> boards.stream().anyMatch(BingoBoard::isBlackBingo);

        while (true) {
            OutputView.printRequestToMarkBingoNumber(bingoSize);
            String bingoNumberToMarkValue = InputView.requestPlayerInput();
            BingoNumber bingoNumberToMark;
            try {
                bingoNumberToMark = BingoNumber.of(bingoNumberToMarkValue, bingoSize);
            } catch (IllegalArgumentException e) {
                OutputView.printNoticeForEmptyBingoNumber();
                continue;
            }

            markBingoNumber(userBingoBoard, computerBingoBoard, bingoNumberToMark, bingoSize);

            if (hasAnyBlackBingo.getAsBoolean()) {
                break;
            }

            BingoNumber computerBingoNumberToMark = computerBingoBoard.getBingoNumberToRemove();
            OutputView.printComputerBingoNumberToMark(computerBingoNumberToMark);

            markBingoNumber(userBingoBoard, computerBingoBoard, computerBingoNumberToMark, bingoSize);

            if (hasAnyBlackBingo.getAsBoolean()) {
                break;
            }
        }
    }

    private static void markBingoNumber(UserBingoBoard userBingoBoard, ComputerBingoBoard computerBingoBoard,
                                        BingoNumber bingoNumberToMark, BingoSize bingoSize) throws IOException {
        userBingoBoard.markBingoNumber(bingoNumberToMark);
        computerBingoBoard.markBingoNumber(bingoNumberToMark);
        OutputView.printBingoBoard(bingoSize, userBingoBoard.getBingoNumbers());

        System.out.println("🍁컴퓨터용 빙고판🍁");
        OutputView.printBingoBoard(bingoSize, computerBingoBoard.getBingoNumbers());
    }
}
