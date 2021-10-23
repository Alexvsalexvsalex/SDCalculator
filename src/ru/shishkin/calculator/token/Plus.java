package ru.shishkin.calculator.token;

public class Plus implements LowPriorityOp {
    @Override
    public String toString() {
        return "PLUS";
    }

    @Override
    public int calc(int a, int b) {
        return a + b;
    }
}
