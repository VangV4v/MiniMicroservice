package com.vang.authadminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthadminserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthadminserviceApplication.class, args);
	}

}
