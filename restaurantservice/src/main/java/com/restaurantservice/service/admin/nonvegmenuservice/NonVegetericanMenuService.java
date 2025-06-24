package com.restaurantservice.service.admin.nonvegmenuservice;

import com.restaurantservice.dtos.requestdtos.menuRequest.MenuRequestDto;
import com.restaurantservice.dtos.responsedtos.menuResponse.MenuResponseDto;

public interface NonVegetericanMenuService {
    MenuResponseDto createNonVegMenu(MenuRequestDto dto);
}
