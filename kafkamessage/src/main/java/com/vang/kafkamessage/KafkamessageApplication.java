package com.vang.kafkamessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;

@SpringBootApplication
public class KafkamessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkamessageApplication.class, args);
	}

	@Bean
	public JsonMessageConverter converter() {
		return new JsonMessageConverter();
	}
}
