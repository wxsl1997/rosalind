package com.wxsl.rosalind.designpattern.creational.abstractfactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("抽象工厂模式")
class DigitalFactoryTest {

    @Test
    @DisplayName("抽象工厂-普通")
    void display() {
        DigitalFactory factory = new AppleFactory();
        Phone phone = factory.createPhone();
        phone.display();
        Pad pad = factory.createPad();
        pad.display();
    }
}
