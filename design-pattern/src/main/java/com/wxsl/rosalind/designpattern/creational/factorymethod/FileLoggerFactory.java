package com.wxsl.rosalind.designpattern.creational.factorymethod;

public class FileLoggerFactory implements LoggerFactory {

    @Override
    public Logger logger() {
        return new FileLogger();
    }
}
