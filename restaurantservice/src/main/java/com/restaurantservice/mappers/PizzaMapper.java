package com.restaurantservice.mappers;

import com.restaurantservice.dtos.responsedtos.menuResponse.MenuResponseDto;
import com.restaurantservice.dtos.responsedtos.menuResponse.PizzaResponseDto;
import com.restaurantservice.entity.Menu;
import com.restaurantservice.entity.Pizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaMapper {
    public static List<PizzaResponseDto> fromPizzaEntity(List<Pizza> menu){
        List<PizzaResponseDto>list=new ArrayList<>();
        for(Pizza pizza: menu){
            PizzaResponseDto pizzaResponseDto=new PizzaResponseDto();
            pizzaResponseDto.setId(pizza.getId());
            pizzaResponseDto.setPizzaSize(pizza.getPizzaSize());
            pizzaResponseDto.setPizzaName(pizza.getPizzaName());
            pizzaResponseDto.setPrice(pizza.getPrice());
            pizzaResponseDto.setQuantity(pizza.getQuantity());
            pizzaResponseDto.setSpicy(pizza.getSpicy());
            list.add(pizzaResponseDto);
        }
        return list;
    }
}
