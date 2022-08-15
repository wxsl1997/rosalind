package com.wxsl.rosalind.dp.structural.composite;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 目录构建
 */
public class Catalog implements Structure {

    private List<Structure> structures = Lists.newArrayList();

    private String catalogName;

    public Catalog(String catalogName) {
        this.catalogName = catalogName;
    }

    public void addStructure(Structure newStructure) {
        structures.add(newStructure);
    }

    @Override
    public void display(String prefix) {
        System.out.println("catalog" + TAB_DELIMITER + prefix + catalogName);
        structures.forEach(structure -> structure.display(prefix + DEFAULT_DELIMITER));
    }
}
