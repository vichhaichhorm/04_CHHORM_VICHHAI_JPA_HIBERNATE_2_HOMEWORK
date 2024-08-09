package com.vichhai.demo_data_jpa_2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Homework 2 JPA2/ Hibernate O4-Chhorm-Vichhai-Homwork2",
				version = "3.14"
		)
)
public class DemoDataJpa2Application {
	public static void main(String[] args) {
		SpringApplication.run(DemoDataJpa2Application.class, args);
	}

}
