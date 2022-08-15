package com.wxsl.rosalind.dp.structural.flyweight;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("享元模式")
class ClassFactoryTest {

    @Test
    @DisplayName("享元测试")
    void object() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Object obj1 = ClassFactory.object("java.lang.Object");
        Object obj2 = ClassFactory.object("java.lang.Object");
        Assertions.assertSame(obj1, obj2);
    }
}
