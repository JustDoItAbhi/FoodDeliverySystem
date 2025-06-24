package com.restaurantservice.mappers;

import com.restaurantservice.dtos.responsedtos.menuResponse.VegMenuResponseDto;
import com.restaurantservice.entity.VegMenu;

import java.util.ArrayList;
import java.util.List;

public class VegMenuMapper {
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
            dto.setSpicy(vegMenu.getSpicy());
            responseDtos.add(dto);
        }
        return responseDtos;
    }
}
