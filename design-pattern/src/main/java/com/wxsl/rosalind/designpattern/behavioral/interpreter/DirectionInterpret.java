package com.wxsl.rosalind.designpattern.behavioral.interpreter;

import lombok.AllArgsConstructor;

/**
 * TerminalExpression
 */
@AllArgsConstructor
public class DirectionInterpret implements Interpreter {

    private String direction;

    @Override
    public String interpret() {
        String result ;
        if ("up".equalsIgnoreCase(direction)) {
            result = "向上";
        } else if ("down".equalsIgnoreCase(direction)) {
            result = "向下";
        } else if ("left".equalsIgnoreCase(direction)) {
            result = "向左";
        } else if ("right".equalsIgnoreCase(direction)) {
            result = "向右";
        } else {
            throw new RuntimeException("error direction");
        }
        return result;
    }
}
