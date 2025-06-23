package com.foodcartservice.cart.responsedtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressesResponseDto {
    private long addressId;
    private int buildingNumber;
    private String street;
    private String city;
    private String country;
    private long pinCode;
}
