package com.CustomerService.kafka;

import com.CustomerService.kafka_email_service.EmailDto;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaProducerFactory {
    @Bean
    public ProducerFactory<String ,EmailDto>producerFactory(){
        Map<String, Object>map=new HashMap<>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstrains.KAKFA_HOST);
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(map,new StringSerializer(), new JsonSerializer());
    }
    @Bean
    public KafkaTemplate<String,EmailDto>kafkaTemplate(ProducerFactory<String,EmailDto>producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
