package com.restaurantservice.mappers;

import com.restaurantservice.dtos.responsedtos.MenuResponseDto;
import com.restaurantservice.dtos.responsedtos.NonVegMenuResponseDto;
import com.restaurantservice.dtos.responsedtos.VegMenuResponseDto;
import com.restaurantservice.entity.Menu;
import com.restaurantservice.entity.NonVegMenu;
import com.restaurantservice.entity.VegMenu;
import com.restaurantservice.exceptions.MenuNullException;

import java.util.ArrayList;
import java.util.List;

public class MenuMappers {

    public static List<NonVegMenuResponseDto> fromNonVegMenuEntity(List<NonVegMenu>nonVegMenusList) {//non veg
        List<NonVegMenuResponseDto>nonVegMenuResponseDtoList=new ArrayList<>();
        if (nonVegMenusList == null || nonVegMenusList.isEmpty()) {
            return nonVegMenuResponseDtoList;
        }
        for(NonVegMenu nonVegMenu:nonVegMenusList){
            NonVegMenuResponseDto nonVegMenuResponseDto=new NonVegMenuResponseDto();
            nonVegMenuResponseDto.setNonVegFoodId(nonVegMenu.getId());
            nonVegMenuResponseDto.setFoodItem(nonVegMenu.getFoodItem());
            nonVegMenuResponseDto.setQuantity(nonVegMenu.getQuantity());
            nonVegMenuResponseDto.setPrice(nonVegMenu.getPrice());
            nonVegMenuResponseDtoList.add(nonVegMenuResponseDto);
        }
        return nonVegMenuResponseDtoList;


//        if (menu.getNonVegMenus() == null) {
//            throw new MenuNullException("YOUR MENU IS NULL CHECK SERVICE LAYER");
//        }
//        List<NonVegMenuResponseDto>responseDtoList=new ArrayList<>();
//        for(NonVegMenu nonveg: menu.getNonVegMenus()) {
//            NonVegMenuResponseDto responseDto = new NonVegMenuResponseDto();
//            responseDto.setNonVegFoodId(nonveg.getId());
//            responseDto.setFoodItem(nonveg.getFoodItem());
//            responseDto.setQuantity(nonveg.getQuantity());
//            responseDto.setPrice(nonveg.getPrice());
//            responseDtoList.add(responseDto);
//        }
//        dto.setNonVegMenusResponse(responseDtoList);
//
//        return dto;
    }
    public static List<VegMenuResponseDto> fromVegMenuEntity(List<VegMenu> vegMenuList){

        List<VegMenuResponseDto>responseDtos=new ArrayList<>();
        if (vegMenuList == null || vegMenuList.isEmpty()) {
            return responseDtos;
        }
        for(VegMenu vegMenu:vegMenuList){
            VegMenuResponseDto dto=new VegMenuResponseDto();
            dto.setVegMenuId(vegMenu.getId());
            dto.setFoodItem(vegMenu.getFoodItem());
            dto.setQuantity(vegMenu.getQuantity());
            dto.setPrice(vegMenu.getPrice());
            responseDtos.add(dto);
        }
        return responseDtos;
    }
    public static MenuResponseDto forMenuOnly(Menu menu){
        MenuResponseDto dto=new MenuResponseDto();
        dto.setMenuId(menu.getId());
        dto.setRestaurantName(menu.getRestName());
        dto.setVegMenusResponse(fromVegMenuEntity(menu.getVegMenus()));
        dto.setNonVegMenusResponse(fromNonVegMenuEntity(menu.getNonVegMenus()));
        return dto;
    }
}
