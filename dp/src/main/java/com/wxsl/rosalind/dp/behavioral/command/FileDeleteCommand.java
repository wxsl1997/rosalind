package com.wxsl.rosalind.dp.behavioral.command;

/**
 * 具体命令类
 */
public class FileDeleteCommand implements FileCommand {

    /**
     * 维系请求文件删除执行者引用
     */
    private FileDeleteExecutor fileDeleteExecutor = new FileDeleteExecutor();

    @Override
    public void action() {
        fileDeleteExecutor.deleteFile();
    }
}
