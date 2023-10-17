package com.wxsl.rosalind.designpattern.creational.factorymethod;

public class ConsoleLogger implements Logger {

    @Override
    public void writeLog() {
        System.out.println("write log : console");
    }
}
