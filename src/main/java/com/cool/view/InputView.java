package com.cool.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static String requestPlayerAnswer() throws IOException {
        return bufferedReader.readLine();
    }

    public static void close() throws IOException {
        bufferedReader.close();
    }
}
