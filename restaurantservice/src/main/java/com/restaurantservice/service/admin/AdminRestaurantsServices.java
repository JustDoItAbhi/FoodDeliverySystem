package com.restaurantservice.service.admin;

import com.restaurantservice.dtos.requestdtos.MenuRequestDto;
import com.restaurantservice.dtos.requestdtos.RestaurantRequestDto;
import com.restaurantservice.dtos.responsedtos.MenuResponseDto;
import com.restaurantservice.dtos.responsedtos.NonVegMenuResponseDto;
import com.restaurantservice.dtos.responsedtos.RestaurantResponseDto;
import com.restaurantservice.dtos.responsedtos.VegMenuResponseDto;

public interface AdminRestaurantsServices {
    RestaurantResponseDto createRestaurant(RestaurantRequestDto dto);
   MenuResponseDto createNonVegMenu(MenuRequestDto dto);
    MenuResponseDto createVegMenu(MenuRequestDto dto);
    RestaurantResponseDto getRestaurantByName(String name);
    MenuResponseDto getById(long id);
    }
