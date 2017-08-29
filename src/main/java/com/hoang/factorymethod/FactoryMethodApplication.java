package com.hoang.factorymethod;

import com.hoang.factorymethod.controller.Shape;
import com.hoang.factorymethod.controller.ShapeFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FactoryMethodApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryMethodApplication.class, args);

		Shape shape = ShapeFactory.getShare("circle");
		shape.draw();

		System.out.println("hello world");
	}

}
