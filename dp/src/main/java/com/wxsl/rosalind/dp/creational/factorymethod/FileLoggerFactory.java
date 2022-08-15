package com.wxsl.rosalind.dp.creational.factorymethod;

public class FileLoggerFactory implements LoggerFactory {

    @Override
    public Logger logger() {
        return new FileLogger();
    }
}
