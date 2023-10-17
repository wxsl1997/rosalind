package com.wxsl.rosalind.designpattern.behavioral.observer.delegate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("事件委派")
class ExamNotifierTest {

    private ExamNotifier examNotifier;

    @BeforeEach
    void setUp() {
        examNotifier = new ExamNotifier();
        examNotifier.register(new GoodStudent(), "easy");
        examNotifier.register(new BadStudent(), "anxious");
    }

    @Test
    @DisplayName("委派测试")
    void exam() throws Exception {
        examNotifier.exam();
    }
}
