package com.restaurantservice.cart.cartdtos.cartrequestdto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateCartRequestDto {
    private String restName;
    List<ItemRequestDto> itemRequestDtoList;

}
