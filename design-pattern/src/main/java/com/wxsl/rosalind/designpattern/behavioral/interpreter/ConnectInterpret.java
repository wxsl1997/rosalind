package com.wxsl.rosalind.designpattern.behavioral.interpreter;

import lombok.AllArgsConstructor;

/**
 * NonTerminalExpression
 */
@AllArgsConstructor
public class ConnectInterpret implements Interpreter {

    private Interpreter leftSentence;

    private Interpreter rightSentence;

    private static final String DEFAULT_INTERPRET_CONNECT_SIGN = ", ";

    @Override
    public String interpret() {
        return leftSentence.interpret() + DEFAULT_INTERPRET_CONNECT_SIGN + rightSentence.interpret();
    }
}
