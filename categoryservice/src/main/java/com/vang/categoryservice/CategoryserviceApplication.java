package com.vang.categoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CategoryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryserviceApplication.class, args);
	}

}
