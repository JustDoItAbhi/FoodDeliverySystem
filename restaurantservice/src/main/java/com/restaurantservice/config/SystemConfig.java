package com.restaurantservice.config;

import com.restaurantservice.dtos.responsedtos.RestaurantResponseDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class SystemConfig {
@Bean
@Qualifier("restaurantRedisTemplate")
public RedisTemplate<String, RestaurantResponseDto>redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, RestaurantResponseDto> template = new RedisTemplate<>();
    template.setConnectionFactory(factory);
    template.setKeySerializer(new GenericToStringSerializer<>(String.class));
    template.setValueSerializer(new Jackson2JsonRedisSerializer<>(RestaurantResponseDto.class));
    template.afterPropertiesSet();
    return template;
}

}
