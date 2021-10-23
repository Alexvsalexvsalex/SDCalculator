package ru.shishkin.calculator.token;

import ru.shishkin.calculator.visitor.TokenVisitor;

public class Number implements Token {
    private final int n;

    public Number(int n) {
        this.n = n;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public int getN() {
        return n;
    }

    @Override
    public String toString() {
        return "NUMBER(" + getN() + ")";
    }
}
