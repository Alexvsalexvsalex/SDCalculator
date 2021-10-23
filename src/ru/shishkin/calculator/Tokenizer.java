package ru.shishkin.calculator;

import ru.shishkin.calculator.token.*;
import ru.shishkin.calculator.token.Number;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static ru.shishkin.calculator.TokenizerState.*;

public class Tokenizer {
    private final StreamHolder stream;
    private TokenizerState state;

    public Tokenizer(InputStream stream) {
        this.stream = new StreamHolder(stream);
        this.state = TokenizerState.START;
    }

    public Token nextToken() {
        return nextTokenImpl(new StringBuilder());
    }

    private Token nextTokenImpl(StringBuilder currentLine) {
        switch (state) {
            case START: {
                int c = stream.take();
                if (c == -1) {
                    state = END;
                    return nextTokenImpl(currentLine);
                } else if (Character.isDigit(c)) {
                    currentLine.append((char) c);
                    state = NUMBER;
                    return nextTokenImpl(currentLine);
                } else if (c == '(') {
                    return new Left();
                } else if (c == ')') {
                    return new Right();
                } else if (c == '+') {
                    return new Plus();
                } else if (c == '-') {
                    return new Minus();
                } else if (c == '*') {
                    return new Multiply();
                } else if (c == '/') {
                    return new Divide();
                } else if (Character.isWhitespace(c)) {
                    return nextTokenImpl(currentLine);
                } else {
                    state = ERROR;
                    return nextTokenImpl(currentLine);
                }
            }
            case NUMBER: {
                int c = stream.look();
                if (Character.isDigit(c)) {
                    currentLine.append((char) c);
                    stream.take();
                    return nextTokenImpl(currentLine);
                } else {
                    state = START;
                    return new Number(Integer.parseInt(currentLine.toString()));
                }
            }
            case ERROR: {
                throw new RuntimeException("Error state of tokenizer");
            }
            case END: {
                return null;
            }
            default: {
                throw new RuntimeException("Unknown state of tokenizer");
            }
        }
    }

    public List<Token> allTokens() {
        List<Token> result = new ArrayList<>();
        while (true) {
            Token token = nextToken();
            if (token == null) {
                break;
            } else {
                result.add(token);
            }
        }
        return result;
    }
}
