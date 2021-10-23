package ru.shishkin.calculator.token;

public class Divide implements HighPriorityOp {
    @Override
    public String toString() {
        return "DIV";
    }

    @Override
    public int calc(int a, int b) {
        return a / b;
    }
}
