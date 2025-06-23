package com.CustomerService.kafka;

import com.CustomerService.kafka_email_service.EmailDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerFactory {

@Bean
    public ConsumerFactory<String, EmailDto>consumerFactory(){
    Map<String,Object>map=new HashMap<>();
    map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaConstrains.KAKFA_HOST);
    map.put(ConsumerConfig.GROUP_ID_CONFIG,KafkaConstrains.KAFKA_GROUP_ID);
    map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    map.put(JsonDeserializer.TRUSTED_PACKAGES,("*"));
    map.put(JsonDeserializer.VALUE_DEFAULT_TYPE, EmailDto.class.getName());
    return new DefaultKafkaConsumerFactory<>(map,new StringDeserializer(),new JsonDeserializer<>(EmailDto.class,false));
}
@Bean
    public ConcurrentKafkaListenerContainerFactory<String,EmailDto>concurrentKafkaListenerContainerFactory(){
    ConcurrentKafkaListenerContainerFactory<String,EmailDto>factory=new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
}
}
