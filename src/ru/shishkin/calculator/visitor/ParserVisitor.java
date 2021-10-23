package ru.shishkin.calculator.visitor;

import ru.shishkin.calculator.token.*;
import ru.shishkin.calculator.token.Number;

import java.util.*;

public class ParserVisitor implements TokenVisitor {
    private List<Token> result = new ArrayList<>();
    private final Deque<Token> stack = new ArrayDeque<>();

    @Override
    public void visit(Number token) {
        result.add(token);
    }

    @Override
    public void visit(Brace token) {
        if (token instanceof Left) {
            stack.addLast(token);
        } else {
            while (!stack.isEmpty() && !(stack.peekLast() instanceof Left)) {
                result.add(stack.pollLast());
            }
            if (stack.isEmpty()) {
                throw new RuntimeException("wrong brackets");
            } else {
                stack.pollLast();
            }
        }
    }

    @Override
    public void visit(Operation token) {
        while (!stack.isEmpty() &&
                stack.peekLast() instanceof Operation &&
                !(token instanceof HighPriorityOp && stack.peekLast() instanceof LowPriorityOp)) {
            result.add(stack.pollLast());
        }
        stack.addLast(token);
    }

    public void finish() {
        while (!stack.isEmpty()) {
            result.add(stack.pollLast());
        }
    }

    public List<Token> getResult() {
        List<Token> res = result;
        result = new ArrayList<>();
        return res;
    }
}
