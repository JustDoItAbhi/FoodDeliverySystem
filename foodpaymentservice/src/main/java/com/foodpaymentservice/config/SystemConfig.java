package com.foodpaymentservice.config;

import com.foodpaymentservice.dtos.orderdtos.PaymentResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SystemConfig {
@Bean
    public RedisTemplate<String ,PaymentResponseDTO>redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String ,PaymentResponseDTO>template=new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(PaymentResponseDTO.class));
        template.afterPropertiesSet();
        return template;
    }
    @Bean
    public RedisCacheManager manager(RedisConnectionFactory factory){
        RedisCacheConfiguration configuration=RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(factory).cacheDefaults(configuration).build();
    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
