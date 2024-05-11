package com.vang.imageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ImageserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageserviceApplication.class, args);
	}

}
