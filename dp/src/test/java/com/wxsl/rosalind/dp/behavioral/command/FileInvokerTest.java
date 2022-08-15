package com.wxsl.rosalind.dp.behavioral.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("命令模式")
class FileInvokerTest {

    @Test
    @DisplayName("批量命令")
    void action() {

        //批处理命令
        FileCommand fileCommand = new FileRenameCommand();
        FileInvoker fileInvoker = new FileInvoker(fileCommand);
        fileInvoker.action();
    }
}
