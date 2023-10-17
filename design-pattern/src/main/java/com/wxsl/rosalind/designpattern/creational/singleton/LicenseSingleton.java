package com.wxsl.rosalind.designpattern.creational.singleton;

/**
 * 许可证
 */
public class LicenseSingleton {

    private LicenseSingleton() {
    }

    /**
     * Initialization on Demand Holder(IoDH)
     */
    private static class IoDHClass {
        private final static LicenseSingleton instance = new LicenseSingleton();
    }

    /**
     * 静态公有工厂方法
     */
    public static LicenseSingleton getInstance() {
        return IoDHClass.instance;
    }
}
