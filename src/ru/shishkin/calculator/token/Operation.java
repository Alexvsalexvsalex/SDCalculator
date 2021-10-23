package ru.shishkin.calculator.token;

import ru.shishkin.calculator.visitor.TokenVisitor;

public interface Operation extends Token {
    @Override
    default void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    int calc(int a, int b);
}
