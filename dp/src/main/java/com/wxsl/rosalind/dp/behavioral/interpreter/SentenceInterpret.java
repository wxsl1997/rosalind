package com.wxsl.rosalind.dp.behavioral.interpreter;

import lombok.AllArgsConstructor;

/**
 * NonTerminalExpression
 */
@AllArgsConstructor
public class SentenceInterpret implements Interpreter {

    private Interpreter direction;
    private Interpreter action;
    private Interpreter time;

    private static final String DEFAULT_DELIMITER = " ";

    @Override
    public String interpret() {
        return direction.interpret() + DEFAULT_DELIMITER + action.interpret() + DEFAULT_DELIMITER + time.interpret();
    }
}
