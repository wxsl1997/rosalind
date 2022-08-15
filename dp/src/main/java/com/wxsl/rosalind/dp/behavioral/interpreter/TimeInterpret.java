package com.wxsl.rosalind.dp.behavioral.interpreter;

import lombok.AllArgsConstructor;

/**
 * TerminalExpression
 */
@AllArgsConstructor
public class TimeInterpret implements Interpreter {

    private String time;

    @Override
    public String interpret() {
        return time;
    }
}
