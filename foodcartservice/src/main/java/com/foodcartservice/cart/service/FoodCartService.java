package com.foodcartservice.cart.service;

import com.foodcartservice.cart.responsedtos.transf.OrderResponseDto;
import com.foodcartservice.cart.responsedtos.RestaurantResponseDto;

public interface FoodCartService {
    RestaurantResponseDto getRestaurant(String restaurantName);
    OrderResponseDto addOrder(String name);
}
