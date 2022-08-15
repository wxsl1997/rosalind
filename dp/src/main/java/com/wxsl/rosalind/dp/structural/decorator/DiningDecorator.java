package com.wxsl.rosalind.dp.structural.decorator;

import lombok.AllArgsConstructor;

/**
 * 抽象装饰类
 */

@AllArgsConstructor
public class DiningDecorator implements Dining {

    /**
     * 被装饰对象
     */
    private Dining dining;

    @Override
    public void eat() {
        dining.eat();
    }
}
