package com.wxsl.rosalind.designpattern.structural.proxy;

/**
 * 静态代理
 */
public class StaticProxyHost implements VisitHost {

    /**
     * 维持真实主题对象的引用
     */
    private final VisitHost visitHost = new DellHost();

    @Override
    public void visit() {
        visitHost.visit();
    }
}
