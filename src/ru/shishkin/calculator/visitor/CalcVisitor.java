package ru.shishkin.calculator.visitor;

import ru.shishkin.calculator.token.Number;
import ru.shishkin.calculator.token.Brace;
import ru.shishkin.calculator.token.Operation;
import ru.shishkin.calculator.token.Token;

import java.util.ArrayDeque;
import java.util.Deque;

public class CalcVisitor implements TokenVisitor {
    private final Deque<Integer> stack = new ArrayDeque<>();

    @Override
    public void visit(Number token) {
        stack.addLast(token.getN());
    }

    @Override
    public void visit(Brace token) {
        throw new RuntimeException("Unexpected token");
    }

    @Override
    public void visit(Operation token) {
        if (stack.size() < 2) {
            throw new RuntimeException("No operands for executing operation");
        }
        int second = stack.pollLast();
        int first = stack.pollLast();
        stack.addLast(token.calc(first, second));
    }

    public int getResult() {
        if (stack.size() != 1) {
            throw new RuntimeException("Count of elements is not 1");
        }
        return stack.pollLast();
    }
}
