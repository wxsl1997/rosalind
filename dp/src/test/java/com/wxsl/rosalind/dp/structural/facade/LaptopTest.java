package com.wxsl.rosalind.dp.structural.facade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("外观模式")
class LaptopTest {

    @Test
    @DisplayName("外观测试")
    void startUp() {
        new DellLaptop().startUp();
    }
}
