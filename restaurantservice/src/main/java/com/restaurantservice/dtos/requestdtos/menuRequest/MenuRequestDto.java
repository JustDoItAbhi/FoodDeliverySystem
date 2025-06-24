package com.restaurantservice.dtos.requestdtos.menuRequest;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MenuRequestDto {
    private String restaurantName;
    private   List<NonVegMenuRequestDto> nonVegMenus;
    private   List<VegMenuRequetDto>vegMenus;
    private List<PizzaRequestDto>pizzaRequestDtoList;
}
