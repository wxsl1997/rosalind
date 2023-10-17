package com.wxsl.rosalind.designpattern.behavioral.observer.delegate;

import com.google.common.collect.Lists;

import java.util.List;

public abstract class Notifiable {

    private List<Event> eventList = Lists.newArrayList();

    /**
     * 注册事件
     */
    public void register(Object object, String methodName, Object... args) {
        eventList.add(new Event(object, methodName, args));
    }

    /**
     * 事件委派
     */
    public void delegateEvent() throws Exception {
        for (Event event : eventList) {
            event.invoke();
        }
    }
}
