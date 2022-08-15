package com.wxsl.rosalind.dp.structural.decorator;

public class SoupDiningDecorator extends DiningDecorator {

    public SoupDiningDecorator(Dining dining) {
        super(dining);
    }

    @Override
    public void eat() {
        soup();
        super.eat();
    }

    /**
     * 透明装饰：喝汤
     */
    private void soup() {
        System.out.println("start enjoying soup before dining .");
    }

}
