package com.vang.sellerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SellerserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellerserviceApplication.class, args);
	}

}