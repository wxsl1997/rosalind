package com.wxsl.rosalind.designpattern.behavioral.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("策略模式")
class LoggerContextTest {

    @Test
    @DisplayName("策略测试")
    void log() {
        LogStrategy logStrategy = new ConsoleStrategy();
        LoggerContext loggerContext = new LoggerContext(logStrategy);
        loggerContext.log("xxx tips");
    }
}
