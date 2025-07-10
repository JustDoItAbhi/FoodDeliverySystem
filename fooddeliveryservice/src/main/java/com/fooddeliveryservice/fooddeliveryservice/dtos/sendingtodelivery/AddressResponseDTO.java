package com.fooddeliveryservice.fooddeliveryservice.dtos.sendingtodelivery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDTO {
    private int buildingNumber;
    private String street;
    private String city;
    private String country;
    private long pinCode;
}
