package com.restaurantservice.service.admin.pizzamenuserv;

import com.restaurantservice.dtos.requestdtos.menuRequest.MenuRequestDto;
import com.restaurantservice.dtos.responsedtos.menuResponse.MenuResponseDto;

public interface PizzaMenuService {
    MenuResponseDto addPizza(MenuRequestDto dto);
}
