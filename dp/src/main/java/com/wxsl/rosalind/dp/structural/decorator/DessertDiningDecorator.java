package com.wxsl.rosalind.dp.structural.decorator;

public class DessertDiningDecorator extends DiningDecorator {

    public DessertDiningDecorator(Dining dining) {
        super(dining);
    }

    @Override
    public void eat() {
        super.eat();
        dessert();
    }

    /**
     * 透明装饰：甜点
     */
    private void dessert() {
        System.out.println("start enjoying dessert after dining .");
    }
}
