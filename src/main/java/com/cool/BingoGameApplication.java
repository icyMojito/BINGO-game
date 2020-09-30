package com.cool;

import com.cool.bingo.BingoSize;
import com.cool.bingo.BingoType;
import com.cool.bingo.PlayerType;
import com.cool.bingo.board.BingoBoard;
import com.cool.bingo.board.BingoBoardFactory;
import com.cool.bingo.number.BingoNumber;
import com.cool.bingo.number.BingoNumbers;
import com.cool.bingo.numberPicker.ComputerBingoNumberPicker;
import com.cool.bingo.numberPicker.ComputerBingoNumberPickerFactory;
import com.cool.exception.InvalidBingoNumberException;
import com.cool.view.InputView;
import com.cool.view.OutputView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BingoGameApplication {
    public static void main(String[] args) throws IOException {
        BingoSize bingoSize = createBingoSize();
        BingoType bingoType = createBingoType();

        BingoNumbers userBingoNumbers = createBingoNumbers(bingoSize);
        BingoBoard userBingoBoard = BingoBoardFactory.createBingoBoard(bingoSize, bingoType, PlayerType.USER,
                                                                       userBingoNumbers);

        BingoNumbers computerBingoNumbers = BingoNumbers.createRandomBingoNumbers(bingoSize);
        BingoBoard computerBingoBoard = BingoBoardFactory.createBingoBoard(bingoSize, bingoType, PlayerType.COMPUTER,
                                                                           computerBingoNumbers);
        ComputerBingoNumberPicker computerBingoNumberPicker =
                ComputerBingoNumberPickerFactory.createBingoNumberPicker(bingoType,
                                                                         computerBingoBoard.getBingoNumbers());

        List<BingoBoard> bingoBoards = Arrays.asList(userBingoBoard, computerBingoBoard);

        while (true) {
            if (isBingoAfterNumberMark(bingoBoards, bingoType, bingoSize, computerBingoNumberPicker)) {
                break;
            }
        }

        OutputView.printGameResult(userBingoBoard);
        InputView.close();
        OutputView.close();
    }

    private static boolean isBingoAfterNumberMark(List<BingoBoard> bingoBoards, BingoType bingoType,
                                                  BingoSize bingoSize,
                                                  ComputerBingoNumberPicker computerBingoNumberPicker) throws IOException {
        for (BingoBoard bingoBoard : bingoBoards) {
            BingoNumber bingoNumberToMark;

            if (bingoBoard.isUserPlayer()) {
                bingoNumberToMark = selectUserBingoNumberToMark(bingoType, bingoSize);
            } else {
                bingoNumberToMark = computerBingoNumberPicker.pickBingoNumber();
                OutputView.printComputerBingoNumberToMark(bingoNumberToMark);
            }

            markNumberInBingoBoards(bingoBoards, bingoNumberToMark);

            if (isAnyBingoInBingoBoards(bingoBoards)) {
                return true;
            }
        }
        return false;
    }

    private static BingoNumber selectUserBingoNumberToMark(BingoType bingoType, BingoSize bingoSize) throws IOException {
        while (true) {
            OutputView.printRequestToMarkBingoNumber(bingoType, bingoSize);
            String bingoNumberToMarkValue = InputView.requestPlayerInput();
            try {
                return BingoNumber.of(bingoNumberToMarkValue, bingoSize);
            } catch (InvalidBingoNumberException | IllegalArgumentException e) {
                OutputView.printNoticeForInvalidBingoNumber(e.getMessage());
            }
        }
    }

    private static void markNumberInBingoBoards(List<BingoBoard> bingoBoards, BingoNumber bingoNumberToMark) throws IOException {
        for (BingoBoard board : bingoBoards) {
            board.mark(bingoNumberToMark);

            if (board.isUserPlayer()) {
                OutputView.printBingoBoard(board.getBingoNumbers());
            }
        }
    }

    private static boolean isAnyBingoInBingoBoards(List<BingoBoard> bingoBoards) throws IOException {
        for (BingoBoard bingoBoard : bingoBoards) {
            if (bingoBoard.isBingo()) {
                OutputView.printWinBingoBoard(bingoBoard.getBingoNumbers(), bingoBoard.getPlayerType());
                return true;
            }
        }
        return false;
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

    private static BingoNumbers createBingoNumbers(BingoSize bingoSize) throws IOException {
        OutputView.printRequestForBingoNumber(bingoSize);

        BingoNumbers bingoNumbers = BingoNumbers.empty();
        while (bingoSize.isBiggerThan(bingoNumbers.getSize())) {
            OutputView.printRequestForNumberInput();
            String bingoNumbersValue = InputView.requestPlayerInput();

            BingoNumbers numbers = BingoNumbers.of(bingoNumbersValue, bingoSize);
            BingoNumbers newBingoNumbers = bingoNumbers.add(numbers);

            if (bingoSize.isSmallerThan(newBingoNumbers.getSize())) {
                newBingoNumbers = newBingoNumbers.reduce(bingoSize);
            }

            bingoNumbers = newBingoNumbers;

            OutputView.printBingoNumbers(bingoNumbers, bingoSize);
        }

        return bingoNumbers;
    }
}
