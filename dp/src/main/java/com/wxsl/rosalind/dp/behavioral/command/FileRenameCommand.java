package com.wxsl.rosalind.dp.behavioral.command;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * 具体命令类
 */
public class FileRenameCommand implements FileCommand {

    /**
     * 命令批处理
     */
    private List<FileCommand> commandList = ImmutableList.<FileCommand>builder()
            .add(new FileDeleteCommand())
            .add(new FileCreateCommand())
            .build();

    @Override
    public void action() {
        commandList.forEach(FileCommand::action);
    }
}
