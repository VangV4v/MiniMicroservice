package com.vang.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.IOException;

@EnableDiscoveryClient
@SpringBootApplication
public class CustomerserviceApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(CustomerserviceApplication.class, args);
//		Server server = ServerBuilder.forPort(2009)
//				.addService(new HelloServiceImpl()).build();
//		server.start();
//		server.awaitTermination();
//
	}

}