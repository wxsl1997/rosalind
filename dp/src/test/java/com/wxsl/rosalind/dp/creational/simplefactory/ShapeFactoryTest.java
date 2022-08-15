package com.wxsl.rosalind.dp.creational.simplefactory;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.wxsl.rosalind.dp.creational.simplefactory.ShapeType.CIRCLE;
import static com.wxsl.rosalind.dp.creational.simplefactory.ShapeType.RECTANGLE;

@DisplayName("工厂模式")
class ShapeFactoryTest {

    /**
     * 测试工厂方法
     */
    @Test
    @DisplayName("工厂方法-普通")
    void createShape() {
        Shape shape = ShapeFactory.createShape(CIRCLE);
        shape.draw();

        shape = ShapeFactory.createShape(RECTANGLE);
        shape.draw();
    }

    @Test

    @DisplayName("工厂方法-意义明确")
    void createCircle() {
        Shape shape = ShapeFactory.createCircle();
        shape.draw();
    }
}
