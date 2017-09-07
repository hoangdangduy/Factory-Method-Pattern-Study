package com.hoang.factorymethod;

import com.hoang.factorymethod.controller.Shape;
import com.hoang.factorymethod.controller.ShapeFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class FactoryMethodApplication {



	public static void main(String[] args) {
		SpringApplication.run(FactoryMethodApplication.class, args);


	}

}
