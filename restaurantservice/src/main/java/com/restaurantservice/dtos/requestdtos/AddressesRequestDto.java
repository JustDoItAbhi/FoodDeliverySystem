package com.restaurantservice.dtos.requestdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressesRequestDto {
    private int buildingNumber;
    private String street;
    private String city;
    private String country;
    private long pinCode;
}
