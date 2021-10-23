package ru.shishkin.calculator.token;

public class Multiply implements HighPriorityOp {
    @Override
    public String toString() {
        return "MUL";
    }

    @Override
    public int calc(int a, int b) {
        return a * b;
    }
}
