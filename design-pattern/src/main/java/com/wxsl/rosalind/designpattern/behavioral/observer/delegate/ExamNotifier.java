package com.wxsl.rosalind.designpattern.behavioral.observer.delegate;

public class ExamNotifier extends Notifiable {

    /**
     * 考试
     */
    public void exam() throws Exception {
        delegateEvent();
    }
}
