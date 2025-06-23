package com.foodcartservice.cart.service;

import com.foodcartservice.cart.responsedtos.*;
import com.foodcartservice.cart.responsedtos.transf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodCartServiceImpl implements FoodCartService{
    @Autowired
    private RedisTemplate<String, RestaurantResponseDto>redisTemplate;

    public FoodCartServiceImpl(RedisTemplate<String, RestaurantResponseDto> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public RestaurantResponseDto getRestaurant(String restaurantName) {
       RestaurantResponseDto dto=redisTemplate.opsForValue().get(restaurantName);
       if (dto!=null){
           dto.setStatus(CartStatus.CARD_ADDED);
       }

        return dto;
    }

    @Override
    public OrderResponseDto addOrder(String name) {
        RestaurantResponseDto dto=redisTemplate.opsForValue().get(name);
        OrderResponseDto orderResponseDto=new OrderResponseDto();
        orderResponseDto.setRestaurantName(dto.getRestaurantName());
        FoodItems items=new FoodItems();
        items.setMessage("here is your order ");
        items.setSpicy(TypesOfSpicy.FULL_SPICY);
        double vegTotalPrice=0.0;
        double nonVegtotalPrice=0.0;
        double vegQuantity=0.0;
        double nonvegQuantity=0.0;

        List<VegfoodItems> vegFoodItemsList=new ArrayList<>();

            for(VegMenuResponseDto vegMenuResponseDto:dto.getMenu().getVegMenusResponse()){
                VegfoodItems vegItems=new VegfoodItems();
                vegItems.setFoodItem(vegMenuResponseDto.getFoodItem());
                vegQuantity+=vegMenuResponseDto.getQuantity();
                vegItems.setQuantity(vegQuantity);
               vegTotalPrice+=vegMenuResponseDto.getQuantity()*vegMenuResponseDto.getPrice();
                vegItems.setPrice(vegTotalPrice);
                vegFoodItemsList.add(vegItems);
            }

        List<NonVegfoodItems>nonVegfoodItemsList=new ArrayList<>();
            for(NonVegMenuResponseDto nonVegMenuResponseDto:dto.getMenu().getNonVegMenusResponse()){
                NonVegfoodItems nonVegfoodItems=new NonVegfoodItems();
                nonVegfoodItems.setFoodItem(nonVegMenuResponseDto.getFoodItem());
                nonvegQuantity+=nonVegMenuResponseDto.getQuantity();
                nonVegfoodItems.setQuantity(nonvegQuantity);
                nonVegfoodItems.setPrice(nonVegMenuResponseDto.getPrice());
                nonVegtotalPrice+= nonVegMenuResponseDto.getQuantity()*nonVegMenuResponseDto.getPrice();
                nonVegfoodItems.setPrice(nonVegtotalPrice);
                nonVegfoodItemsList.add(nonVegfoodItems);
            }

        items.setNonVegfoodItems(nonVegfoodItemsList);
        items.setVegfoodItems(vegFoodItemsList);
        items.setQuantity(vegQuantity+nonvegQuantity);

        orderResponseDto.setFoodItems(items);
        orderResponseDto.setTotalPrice(vegTotalPrice+nonVegtotalPrice);


        return orderResponseDto;
    }
}
