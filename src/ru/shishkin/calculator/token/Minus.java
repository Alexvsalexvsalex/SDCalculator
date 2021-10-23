package ru.shishkin.calculator.token;

public class Minus implements LowPriorityOp {
    @Override
    public String toString() {
        return "MINUS";
    }

    @Override
    public int calc(int a, int b) {
        return a - b;
    }
}
