package com.wxsl.rosalind.dp.structural.decorator;

/**
 * 具体组件：晚餐
 */
public class Dinner implements Dining {
    @Override
    public void eat() {
        System.out.println("start enjoying food in dinner .");
    }
}
