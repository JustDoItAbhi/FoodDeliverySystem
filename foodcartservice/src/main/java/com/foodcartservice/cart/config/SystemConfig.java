package com.foodcartservice.cart.config;

import com.foodcartservice.cart.dtos.cartdtos.CartResponseDto;
import com.foodcartservice.cart.dtos.orderdtos.OrderResponseDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SystemConfig {
    @Bean
    @Qualifier("order")
    public RedisTemplate<String, OrderResponseDto>redisTemplate(RedisConnectionFactory factory){
    RedisTemplate<String,OrderResponseDto>template=new RedisTemplate<>();
   template.setConnectionFactory(factory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new Jackson2JsonRedisSerializer<>(OrderResponseDto.class));
    template.afterPropertiesSet();
   return template;
}
    @Bean
    @Qualifier("cart")
    public RedisTemplate<String, CartResponseDto>redisTemplates(RedisConnectionFactory factory){
        RedisTemplate<String,CartResponseDto>template=new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(CartResponseDto.class));
        template.afterPropertiesSet();
        return template;
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//        template.setKeySerializer(new StringRedisSerializer());
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(mapper, Object.class);
//
//        template.setValueSerializer(serializer);
//        template.afterPropertiesSet();
//        return template;
//    }
}
