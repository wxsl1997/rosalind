package com.wxsl.rosalind.designpattern.structural.composite;

import lombok.AllArgsConstructor;

/**
 * 节点构建
 */

@AllArgsConstructor
public class Node implements Structure {

    private String nodeName;

    @Override
    public void display(String prefix) {
        System.out.println("node" + TAB_DELIMITER + prefix + nodeName);
    }
}
