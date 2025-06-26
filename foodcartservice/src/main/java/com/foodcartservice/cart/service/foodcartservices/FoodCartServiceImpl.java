package com.foodcartservice.cart.service.foodcartservices;

import com.foodcartservice.cart.dtos.orderdtos.CartStatus;
import com.foodcartservice.cart.dtos.cartdtos.CartResponseDto;
import com.foodcartservice.cart.restcalls.RestClientCalls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class FoodCartServiceImpl implements FoodCartService{
    @Qualifier("cart")
    private RedisTemplate<String,CartResponseDto>redisTemplates;
    @Autowired
    private RestClientCalls clientCalls;

    public FoodCartServiceImpl(RedisTemplate<String, CartResponseDto> redisTemplates) {
        this.redisTemplates = redisTemplates;
    }

    @Override
    @Cacheable
    public CartResponseDto getCart(String restName) {
        CartResponseDto cartResponseDto= redisTemplates.opsForValue().get(restName);//redis call
//        CartResponseDto cartResponseDto=clientCalls.responseEntity(restName);//http call
        if (cartResponseDto.getTotalPrice()<=0.0||
                cartResponseDto.getRestName() == null ||
                cartResponseDto.getRestaurantId()<=0||
                cartResponseDto.getTotalQuantity()<=0){
            cartResponseDto.setCartStatus(CartStatus.CART_CANCEL);
        }
        cartResponseDto.setCartStatus(CartStatus.CARD_ADDED);
        return cartResponseDto;
    }
}
