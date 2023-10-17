package com.wxsl.rosalind.designpattern.structural.bridge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pen {

    /**
     * 独立变化的维度
     */
    private Color color;

    protected abstract String size();

    public void draw() {
        System.out.println("size:" + size());
        System.out.println("color:" + color);
    }
}
