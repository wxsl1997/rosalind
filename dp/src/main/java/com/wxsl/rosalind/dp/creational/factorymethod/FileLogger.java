package com.wxsl.rosalind.dp.creational.factorymethod;

public class FileLogger implements Logger {

    @Override
    public void writeLog() {
        System.out.println("write log : file");
    }
}
