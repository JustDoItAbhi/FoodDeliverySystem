package com.restaurantservice.mappers;

import com.restaurantservice.dtos.responsedtos.menuResponse.MenuResponseDto;
import com.restaurantservice.dtos.responsedtos.menuResponse.NonVegMenuResponseDto;
import com.restaurantservice.dtos.responsedtos.menuResponse.VegMenuResponseDto;
import com.restaurantservice.entity.Menu;
import com.restaurantservice.entity.NonVegMenu;
import com.restaurantservice.entity.VegMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuMappers {

    public static MenuResponseDto forNonVegOnly(Menu menu){
        MenuResponseDto dto=new MenuResponseDto();
        dto.setMenuId(menu.getId());
        dto.setRestaurantName(menu.getRestName());
        dto.setNonVegMenusResponse(NonVegMapper.fromNonVegMenuEntity(menu.getNonVegMenus()));
        return dto;
    }
    public static MenuResponseDto forVegOnly(Menu menu){
        MenuResponseDto dto=new MenuResponseDto();
        dto.setMenuId(menu.getId());
        dto.setRestaurantName(menu.getRestName());
        dto.setVegMenusResponse(VegMenuMapper.fromVegMenuEntity(menu.getVegMenus()));
        return dto;
    }
    public static MenuResponseDto forPizzaOnly(Menu menu){
        MenuResponseDto dto=new MenuResponseDto();
        dto.setMenuId(menu.getId());
        dto.setRestaurantName(menu.getRestName());
        dto.setPizzaResponseDtoList(PizzaMapper.fromPizzaEntity(menu.getPizzaList()));
        return dto;
    }
    public static MenuResponseDto fullMenuMapper(Menu menu){
        MenuResponseDto dto=new MenuResponseDto();
        dto.setMenuId(menu.getId());
        dto.setRestaurantName(menu.getRestName());
        dto.setVegMenusResponse(VegMenuMapper.fromVegMenuEntity(menu.getVegMenus()));
        dto.setNonVegMenusResponse(NonVegMapper.fromNonVegMenuEntity(menu.getNonVegMenus()));
        dto.setPizzaResponseDtoList(PizzaMapper.fromPizzaEntity(menu.getPizzaList()));
        return dto;
    }
}
