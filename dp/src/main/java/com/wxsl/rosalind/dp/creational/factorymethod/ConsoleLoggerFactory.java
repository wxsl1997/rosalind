package com.wxsl.rosalind.dp.creational.factorymethod;

public class ConsoleLoggerFactory implements LoggerFactory {

    @Override
    public Logger logger() {
        return new ConsoleLogger();
    }
}
