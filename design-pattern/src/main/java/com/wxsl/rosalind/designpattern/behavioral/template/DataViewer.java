package com.wxsl.rosalind.designpattern.behavioral.template;

public abstract class DataViewer {

    public void view(String data) {
        System.out.println("start handle data");
        display(data);
        System.out.println("end handle data");
    }

    /**
     * 抽象方法
     */
    protected abstract void display(String data);

}
