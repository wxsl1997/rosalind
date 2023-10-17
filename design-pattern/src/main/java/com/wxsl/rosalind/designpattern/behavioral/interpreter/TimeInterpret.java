package com.wxsl.rosalind.designpattern.behavioral.interpreter;

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
