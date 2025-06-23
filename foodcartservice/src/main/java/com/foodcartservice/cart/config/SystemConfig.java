package com.foodcartservice.cart.config;

import com.foodcartservice.cart.responsedtos.RestaurantResponseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class SystemConfig {
@Bean
    public RedisTemplate<String, RestaurantResponseDto>redisTemplate(RedisConnectionFactory factory){
    RedisTemplate<String,RestaurantResponseDto>template=new RedisTemplate<>();
   template.setConnectionFactory(factory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new Jackson2JsonRedisSerializer<>(RestaurantResponseDto.class));
    template.afterPropertiesSet();
   return template;
}
}
