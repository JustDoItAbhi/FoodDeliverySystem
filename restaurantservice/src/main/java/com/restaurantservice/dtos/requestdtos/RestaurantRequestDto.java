package com.restaurantservice.dtos.requestdtos;

import com.restaurantservice.dtos.requestdtos.menuRequest.MenuRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantRequestDto {
    private String restaurantName;
    private List<AddressesRequestDto> address;
    private MenuRequestDto menu;
}
