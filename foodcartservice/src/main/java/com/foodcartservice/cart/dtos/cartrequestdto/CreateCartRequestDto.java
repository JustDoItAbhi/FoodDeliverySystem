package com.foodcartservice.cart.dtos.cartrequestdto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateCartRequestDto {
    private long id;
    private String resturantName;
    List<ItemsRequestDto> itemsRequestDtoList;

}
