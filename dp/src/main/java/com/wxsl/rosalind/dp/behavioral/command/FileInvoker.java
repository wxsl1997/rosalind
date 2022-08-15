package com.wxsl.rosalind.dp.behavioral.command;

import lombok.AllArgsConstructor;

/**
 * 调用者
 */
@AllArgsConstructor
public class FileInvoker {

    /**
     * 维系抽象命令对象引用
     */
    private FileCommand fileCommand;

    /**
     * 命令请求
     */
    public void action() {
        fileCommand.action();
    }
}
