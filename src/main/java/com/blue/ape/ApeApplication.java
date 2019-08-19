package com.blue.ape;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.blue.ape.dao")
@ComponentScan(basePackages = {"com.blue.ape"})
public class ApeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApeApplication.class, args);
	}

}
