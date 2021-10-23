package ru.shishkin.calculator;

import java.io.IOException;
import java.io.InputStream;

public class StreamHolder {
    private int current;
    private final InputStream stream;

    public StreamHolder(InputStream stream) {
        this.stream = stream;
        updateCurrent();
    }

    public int look() {
        return current;
    }

    public int take() {
        int res = current;
        if (current >= 0) {
            updateCurrent();
        }
        return res;
    }

    private void updateCurrent() {
        try {
            current = stream.read();
        } catch (IOException e) {
            throw new RuntimeException("Error in reading stream");
        }
    }
}
