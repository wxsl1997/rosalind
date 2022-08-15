package com.wxsl.rosalind.dp.creational.factorymethod;

public interface LoggerFactory {

    /**
     * 抽象工厂方法
     */
    Logger logger();

    /**
     * 工厂方法隐藏
     */
    default void writeLog() {
        logger().writeLog();
    }
}
