package com.wxsl.rosalind.dp.creational.simplefactory;


public class ShapeFactory {

    /**
     * 创建 Shape 对象
     */
    static Shape createShape(ShapeType type) {

        if (ShapeType.CIRCLE.equals(type)) {
            return new Circle();
        }

        if (ShapeType.RECTANGLE.equals(type)) {
            return new Rectangle();
        }

        throw new UnsupportedOperationException();
    }

    /**
     * 创建Circle对象
     */
    public static Shape createCircle() {
        return new Circle();
    }
}
