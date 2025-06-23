package com.restaurantservice.dtos.responsedtos;

import com.restaurantservice.dtos.requestdtos.NonVegMenuRequestDto;
import com.restaurantservice.dtos.requestdtos.VegMenuRequetDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuResponseDto {
    private long menuId;
    private String restaurantName;
    List<NonVegMenuResponseDto> nonVegMenusResponse;
    List<VegMenuResponseDto>vegMenusResponse;
}
