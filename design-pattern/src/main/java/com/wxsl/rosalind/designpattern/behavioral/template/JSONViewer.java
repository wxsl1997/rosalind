package com.wxsl.rosalind.designpattern.behavioral.template;

public class JSONViewer extends DataViewer {

    @Override
    protected void display(String data) {
        System.out.println("json:" + data);
    }
}
