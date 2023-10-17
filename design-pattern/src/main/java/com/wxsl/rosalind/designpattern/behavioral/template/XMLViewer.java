package com.wxsl.rosalind.designpattern.behavioral.template;

public class XMLViewer extends DataViewer {

    @Override
    protected void display(String data) {
        System.out.println("xml:" + data);
    }
}
