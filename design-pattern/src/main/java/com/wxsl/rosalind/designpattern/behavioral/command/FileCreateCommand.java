package com.wxsl.rosalind.designpattern.behavioral.command;

/**
 * 具体命令类
 */
public class FileCreateCommand implements FileCommand {

    /**
     * 维系请求文件创建执行者引用
     */
    private FileCreateExecutor fileCreateExecutor = new FileCreateExecutor();

    @Override
    public void action() {
        fileCreateExecutor.createFile();
    }
}
