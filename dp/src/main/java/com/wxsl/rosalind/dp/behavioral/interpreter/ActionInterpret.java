package com.wxsl.rosalind.dp.behavioral.interpreter;

import lombok.AllArgsConstructor;

/**
 * TerminalExpression
 */
@AllArgsConstructor
public class ActionInterpret implements Interpreter {

    private String action;

    @Override
    public String interpret() {
        String result;
        if ("run".equalsIgnoreCase(action)) {
            result = "快跑";
        } else if ("walk".equalsIgnoreCase(action)) {
            result = "慢走";
        } else {
            throw new RuntimeException("error action");
        }
        return result;
    }
}
