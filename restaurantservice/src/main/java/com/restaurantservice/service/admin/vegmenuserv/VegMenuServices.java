package com.restaurantservice.service.admin.vegmenuserv;

import com.restaurantservice.dtos.requestdtos.menuRequest.MenuRequestDto;
import com.restaurantservice.dtos.responsedtos.menuResponse.MenuResponseDto;

public interface VegMenuServices {
    MenuResponseDto createVegMenu(MenuRequestDto dto);
}
