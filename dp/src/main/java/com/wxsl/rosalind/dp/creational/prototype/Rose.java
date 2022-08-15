package com.wxsl.rosalind.dp.creational.prototype;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 玫瑰
 */
@NoArgsConstructor
@Data
public class Rose {

    /**
     * 玫瑰名字
     */
    private String name;

    /**
     * 玫瑰颜色
     */
    private String color;

    /**
     * 玫瑰含意
     */
    private List<String> implications;

    public Rose(String name, String color) {
        this.name = name;
        this.color = color;
    }
}
