package com.wxsl.rosalind.designpattern.creational.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("单例模式")
class LicenseSingletonTest {

    @Test
    @DisplayName("登记式单例模式")
    void getInstance() {
        LicenseSingleton ls1 = LicenseSingleton.getInstance();
        LicenseSingleton ls2 = LicenseSingleton.getInstance();
        Assertions.assertSame(ls1, ls2);
    }
}
