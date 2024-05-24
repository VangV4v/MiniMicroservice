package com.vang.sellerservice.beanconfiguation;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguation {

    @Bean
    public NewTopic registrationSeller() {
        return new NewTopic("registrationSeller", 1, (short) 1);
    }

}