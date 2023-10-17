package com.wxsl.rosalind.designpattern.behavioral.strategy;

/**
 * 具体策略类
 */
public class FileStrategy implements LogStrategy {

    @Override
    public void display(String data) {
        System.out.println("file:" + data);
    }
}
