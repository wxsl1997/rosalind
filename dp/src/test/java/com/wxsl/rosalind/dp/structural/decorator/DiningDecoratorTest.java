package com.wxsl.rosalind.dp.structural.decorator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("装饰者模式")
class DiningDecoratorTest {

    @Test
    @DisplayName("透明装饰")
    void eat() {

        Dining dining = new Dinner();

        /*
         * 首次装饰
         */
        DiningDecorator diningDecorator = new SoupDiningDecorator(dining);

        /*
         * 再次装饰
         */
        diningDecorator = new DessertDiningDecorator(diningDecorator);
        diningDecorator.eat();
    }
}
