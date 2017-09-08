package com.hoang.controller;

public class ShapeFactory {
    public static Shape getShare(String shape) {
        if (shape.equalsIgnoreCase("circle")) {
            Shape shape1 = new Circle();
            return shape1;
        } else if (shape.equalsIgnoreCase("square")) {
            Shape shape1 = new Square();
            return shape1;
        } else {
            Shape shape1 = new Rectangle();
            return shape1;
        }
    }
}
