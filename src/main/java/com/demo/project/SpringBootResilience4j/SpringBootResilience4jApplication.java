package com.demo.project.SpringBootResilience4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringBootResilience4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootResilience4jApplication.class, args);
	}

}
