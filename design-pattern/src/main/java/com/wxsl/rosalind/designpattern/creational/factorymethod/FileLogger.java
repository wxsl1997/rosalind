package com.wxsl.rosalind.designpattern.creational.factorymethod;

public class FileLogger implements Logger {

    @Override
    public void writeLog() {
        System.out.println("write log : file");
    }
}
