package com.restaurantservice.mappers;

import com.restaurantservice.dtos.responsedtos.menuResponse.NonVegMenuResponseDto;
import com.restaurantservice.entity.NonVegMenu;

import java.util.ArrayList;
import java.util.List;

public class NonVegMapper {
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
            nonVegMenuResponseDto.setSpicy(nonVegMenu.getSpicy());

            nonVegMenuResponseDtoList.add(nonVegMenuResponseDto);

        }
        return nonVegMenuResponseDtoList;

    }
}
