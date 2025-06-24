package com.restaurantservice.dtos.responsedtos;

import com.restaurantservice.dtos.responsedtos.menuResponse.MenuResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantResponseDto {
    private long restaurantId;
    private String restaurantName;
    private List<AddressesResponseDto> address;
    private MenuResponseDto menu;
}
