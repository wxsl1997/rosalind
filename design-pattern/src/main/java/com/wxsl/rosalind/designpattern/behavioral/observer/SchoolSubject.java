package com.wxsl.rosalind.designpattern.behavioral.observer;

import java.util.Observable;

/**
 * 具体目标类
 */
public class SchoolSubject extends Observable {

    /**
     * 考试
     */
    public void exam() {
        setChanged();
        notifyObservers(NoticeType.EXAM);
    }

    /**
     * 放假
     */
    public void holiday() {
        setChanged();
        notifyObservers(NoticeType.HOLIDAY);
    }
}
