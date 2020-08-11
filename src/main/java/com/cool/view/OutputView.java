package com.cool.view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputView {
    private static final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

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

    public static void close() throws IOException {
        bufferedWriter.close();
    }
}
