package com.foodorderservice.RedisConfig;

import com.foodorderservice.orderdtos.OrderResponseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;


@Configuration
public class SystemConfig {
    @Bean
    public RedisTemplate<String, OrderResponseDto>redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,OrderResponseDto>template=new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(OrderResponseDto.class));
//        template.afterPropertiesSet();
        template.setConnectionFactory(factory);
        return template;
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
