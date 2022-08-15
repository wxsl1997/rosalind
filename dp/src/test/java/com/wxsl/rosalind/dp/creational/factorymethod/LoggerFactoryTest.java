package com.wxsl.rosalind.dp.creational.factorymethod;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("工厂方法")
class LoggerFactoryTest {


    @Test
    @DisplayName("工厂方法-普通")
    void logger() {

        LoggerFactory loggerFactory = new ConsoleLoggerFactory();
        Logger logger = loggerFactory.logger();
        logger.writeLog();

        loggerFactory = new FileLoggerFactory();
        logger = loggerFactory.logger();
        logger.writeLog();
    }

    @Test
    @DisplayName("工厂方法-隐藏")
    void writeLog() {
        LoggerFactory loggerFactory = new ConsoleLoggerFactory();
        loggerFactory.writeLog();
    }
}
