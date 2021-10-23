package ru.shishkin.calculator.visitor;

import ru.shishkin.calculator.token.Number;
import ru.shishkin.calculator.token.Brace;
import ru.shishkin.calculator.token.Operation;

public interface TokenVisitor {
    void visit(Number token);

    void visit(Brace token);

    void visit(Operation token);
}
