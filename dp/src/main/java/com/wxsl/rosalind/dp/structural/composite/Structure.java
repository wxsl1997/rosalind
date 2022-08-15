package com.wxsl.rosalind.dp.structural.composite;

/**
 * 抽象构件
 */
public interface Structure {
    /**
     * 安全组合模式
     */
    void display(String prefix);

    String DEFAULT_DELIMITER = "·····";

    String TAB_DELIMITER = "\t";
}
