package com.restaurantservice.service.client;

import com.restaurantservice.dtos.responsedtos.RestaurantResponseDto;

public interface ClientRestaurantService {
    RestaurantResponseDto getRestaurantByName(String name);
}
