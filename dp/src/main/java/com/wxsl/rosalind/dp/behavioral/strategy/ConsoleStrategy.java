package com.wxsl.rosalind.dp.behavioral.strategy;

/**
 * 具体策略类
 */
public class ConsoleStrategy implements LogStrategy {

    @Override
    public void display(String data) {
        System.out.println("console:" + data);
    }
}
