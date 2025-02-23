package by.sadovnick.singletones;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BufferedReaderInstance {

    private static BufferedReaderInstance INSTANCE;
    private final BufferedReader reader;

    private BufferedReaderInstance() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static BufferedReaderInstance getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BufferedReaderInstance();
        }
        return INSTANCE;
    }

    public BufferedReader getReader() {
        return reader;
    }
}
