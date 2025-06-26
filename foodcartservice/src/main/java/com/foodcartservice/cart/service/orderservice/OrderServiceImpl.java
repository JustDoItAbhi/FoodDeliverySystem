package com.foodcartservice.cart.service.orderservice;

import com.foodcartservice.cart.dtos.cartdtos.CartResponseDto;
import com.foodcartservice.cart.dtos.orderdtos.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderServices{
    @Autowired
    @Qualifier("cart")
    private RedisTemplate<String, CartResponseDto>redisTemplate;
    @Autowired
    @Qualifier("order")
    private RedisTemplate<String,OrderResponseDto>orderResponseDtoRedisTemplate;

    @Override
    public OrderResponseDto createOrder(String name) {
        CartResponseDto cartResponseDto=redisTemplate.opsForValue().get(name);
        OrderResponseDto dto=new OrderResponseDto();
        dto.setCartId(cartResponseDto.getCartId());
        dto.setCartStatus(cartResponseDto.getCartStatus());
        dto.setRestaurantId(cartResponseDto.getRestaurantId());
        dto.setRestName(cartResponseDto.getRestName());
        dto.setTotalQuantity(cartResponseDto.getTotalQuantity());
        dto.setTotalPrice(cartResponseDto.getTotalPrice());
        orderResponseDtoRedisTemplate.delete(name);
        orderResponseDtoRedisTemplate.opsForValue().set(name,dto);
        return dto;
    }

    @Override
    public OrderResponseDto getOrderByName(String name) {
        OrderResponseDto dto=orderResponseDtoRedisTemplate.opsForValue().get(name);
        if(dto==null){
            throw new RuntimeException("ORDER DTO NOT FETCHED BY REDIS "+ name);
        }
        return dto;
    }

}
