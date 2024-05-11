package com.vang.customerservice.beanconfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaBean {

    @Bean
    public NewTopic sendMailCreateCustomer() {

        return new NewTopic("sendmailcreatecustomer", 1, (short) 1);
    }

    @Bean
    public NewTopic notifyRegistrationCustomer() {
        return new NewTopic("notifyRegistrationCustomer", 1, (short)1);
    }
}
