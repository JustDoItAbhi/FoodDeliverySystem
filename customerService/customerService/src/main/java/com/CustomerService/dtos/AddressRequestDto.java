package com.CustomerService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDto {
    private int buildingNumber;
    private String street;
    private String city;
    private String country;
    private long pinCode;
}
