package com.wxsl.rosalind.dp.structural.proxy;

/**
 * 静态代理
 */
public class StaticProxyHost implements VisitHost {

    /**
     * 维持真实主题对象的引用
     */
    private VisitHost visitHost = new DellHost();

    @Override
    public void visit() {
        visitHost.visit();
    }
}
