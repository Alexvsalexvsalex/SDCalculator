package ru.shishkin.calculator.token;

import ru.shishkin.calculator.visitor.TokenVisitor;

public interface Brace extends Token {
    @Override
    default void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
