package com.restaurantservice.service.admin;

import com.restaurantservice.dtos.requestdtos.menuRequest.MenuRequestDto;
import com.restaurantservice.dtos.requestdtos.RestaurantRequestDto;
import com.restaurantservice.dtos.responsedtos.menuResponse.MenuResponseDto;
import com.restaurantservice.dtos.responsedtos.RestaurantResponseDto;

public interface AdminRestaurantsServices {
    RestaurantResponseDto createRestaurant(RestaurantRequestDto dto);

    RestaurantResponseDto getRestaurantByName(String name);
    MenuResponseDto getById(long id);

    }
