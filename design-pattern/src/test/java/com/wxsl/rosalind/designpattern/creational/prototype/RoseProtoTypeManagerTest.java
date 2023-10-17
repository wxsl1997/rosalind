package com.wxsl.rosalind.designpattern.creational.prototype;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("原型模式")
class RoseProtoTypeManagerTest {

    @BeforeAll
    static void setUp() {
        List<Rose> roseList = Lists.newArrayList();

        Rose blueLover = new Rose("blueLover", "blue");
        blueLover.setImplications(Lists.newArrayList("清纯", "忠诚"));
        roseList.add(blueLover);

        Rose corolla = new Rose("corolla", "red");
        corolla.setImplications(Lists.newArrayList("幸福", "浪漫"));
        roseList.add(corolla);

        Rose diana = new Rose("diana", "pink");
        diana.setImplications(Lists.newArrayList("优雅", "温柔"));
        roseList.add(diana);

        RoseProtoTypeManager.getInstance().setRoseMap(Maps.uniqueIndex(roseList, Rose::getName));
    }

    @Test
    @DisplayName("原型管理器")
    void getClonedRose() {
        RoseProtoTypeManager roseProtoTypeManager = RoseProtoTypeManager.getInstance();
        Rose rose = roseProtoTypeManager.getClonedRose("diana");
        System.out.println(rose);
    }
}
