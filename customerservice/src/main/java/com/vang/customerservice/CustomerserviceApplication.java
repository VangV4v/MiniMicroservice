package com.vang.customerservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@EnableDiscoveryClient
@SpringBootApplication
public class CustomerserviceApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(CustomerserviceApplication.class, args);
	}

	@Bean
	public NewTopic sendMailCreateCustomer() {

		return new NewTopic("sendmailcreatecustomer", 1, (short) 1);
	}
}