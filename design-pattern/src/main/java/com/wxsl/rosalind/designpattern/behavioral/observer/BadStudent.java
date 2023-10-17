package com.wxsl.rosalind.designpattern.behavioral.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 具体观察者
 */
public class BadStudent implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        NoticeType noticeType = (NoticeType) arg;
        switch (noticeType) {
            case EXAM: {
                System.out.println("bad student:go anxious");
                break;
            }
            case HOLIDAY: {
                System.out.println("bad student:start play game all night");
                break;
            }
        }
    }
}
