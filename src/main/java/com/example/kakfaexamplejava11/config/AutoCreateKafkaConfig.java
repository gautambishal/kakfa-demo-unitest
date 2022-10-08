package com.example.kakfaexamplejava11.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AutoCreateKafkaConfig {

    @Bean
    public NewTopic newTopic(){
        return TopicBuilder.name("demography")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
