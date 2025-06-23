package com.restaurantservice.dtos.requestdtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MenuRequestDto {
    private String restaurantName;
    List<NonVegMenuRequestDto> nonVegMenus;
    List<VegMenuRequetDto>vegMenus;
}
