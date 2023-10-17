package com.wxsl.rosalind.designpattern.behavioral.template;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("模板模式")
class DataViewerTest {

    @Test
    @DisplayName("模板测试")
    void view() {
        DataViewer viewer = new XMLViewer();
        viewer.view("xxx is best");
    }
}
