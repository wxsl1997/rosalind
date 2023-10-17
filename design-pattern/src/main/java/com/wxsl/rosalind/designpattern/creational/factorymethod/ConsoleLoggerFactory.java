package com.wxsl.rosalind.designpattern.creational.factorymethod;

public class ConsoleLoggerFactory implements LoggerFactory {

    @Override
    public Logger logger() {
        return new ConsoleLogger();
    }
}
