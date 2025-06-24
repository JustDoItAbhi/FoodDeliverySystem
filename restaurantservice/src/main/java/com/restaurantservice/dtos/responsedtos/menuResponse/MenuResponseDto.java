package com.restaurantservice.dtos.responsedtos.menuResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuResponseDto {
    private long menuId;
    private String restaurantName;
    private   List<NonVegMenuResponseDto> nonVegMenusResponse;
    private  List<VegMenuResponseDto>vegMenusResponse;
    private List<PizzaResponseDto>pizzaResponseDtoList;
}
