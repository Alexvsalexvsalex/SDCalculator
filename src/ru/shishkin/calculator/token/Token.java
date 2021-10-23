package ru.shishkin.calculator.token;

import ru.shishkin.calculator.visitor.TokenVisitor;

public interface Token {
    void accept(TokenVisitor visitor);
}
