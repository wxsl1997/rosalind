package com.wxsl.rosalind.designpattern.behavioral.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 具体观察者
 */
public class GoodStudent implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        NoticeType noticeType = (NoticeType) arg;
        switch (noticeType) {
            case EXAM: {
                System.out.println("good student:go easy");
                break;
            }
            case HOLIDAY: {
                System.out.println("good student:start enjoy colorful life");
                break;
            }
        }
    }
}
