package com.wxsl.rosalind.designpattern.structural.composite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("组合模式")
class StructureTest {

    @Test
    @DisplayName("组合测试")
    void display() {
        Catalog root = new Catalog("root");

        Catalog imgCatalog = new Catalog("imgCatalog");
        Node indexNode = new Node("indexNode.html");
        root.addStructure(imgCatalog);
        root.addStructure(indexNode);

        Node imgNode1 = new Node("imgNode1.png");
        Node imgNode2 = new Node("imgNode2.png");
        imgCatalog.addStructure(imgNode1);
        imgCatalog.addStructure(imgNode2);

        root.display(Structure.TAB_DELIMITER);
    }
}
