package com.restaurantservice.config;

import com.restaurantservice.cart.cartdtos.CartResponseDto;
import com.restaurantservice.dtos.responsedtos.RestaurantResponseDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

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

        @Bean
        @Qualifier("cartRedisTemplate")
        public RedisTemplate<String, CartResponseDto>cartRedisTemplate(RedisConnectionFactory factory) {
            RedisTemplate<String, CartResponseDto> template = new RedisTemplate<>();
            template.setConnectionFactory(factory);
            template.setKeySerializer(new StringRedisSerializer());
            template.setValueSerializer(new Jackson2JsonRedisSerializer<>(CartResponseDto.class));
//            template.afterPropertiesSet();
            return template;
        }
    @Bean
    @Qualifier("cartRedisTemplate")
    public RedisCacheManager cartRedis(RedisConnectionFactory factory) {
        RedisCacheConfiguration configuration=RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJackson2JsonRedisSerializer())
                );

        return RedisCacheManager.builder(factory).cacheDefaults(configuration).build();
    }

}
