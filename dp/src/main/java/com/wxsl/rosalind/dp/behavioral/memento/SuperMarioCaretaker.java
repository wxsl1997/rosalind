package com.wxsl.rosalind.dp.behavioral.memento;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 管理员
 */
public class SuperMarioCaretaker {

    private Map<String, SuperMarioMemento> mementoMap = Maps.newHashMap();

    /**
     * 备份
     */
    public void backup(String msg, SuperMarioMemento memento) {
        mementoMap.put(msg, memento);
    }

    /**
     * 获取存档
     */
    public SuperMarioMemento getMemento(String msg) {
        return mementoMap.get(msg);
    }

}
