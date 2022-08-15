package com.wxsl.rosalind.dp.behavioral.interpreter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("解释器模式")
class InstructionHandlerTest {

    @Test
    @DisplayName("解释器测试")
    void resolve() {
        String instruction = "up walk 5 and left run 10 and up walk 10";
        InstructionHandler handler = new InstructionHandler();
        handler.resolve(instruction);
        handler.explain();
    }
}
