package com.wxsl.rosalind.dp.structural.proxy;

/**
 * 真实主角角色
 */
public class DellHost implements VisitHost {

    @Override
    public void visit() {
        System.out.println("visit succeed");
    }
}
