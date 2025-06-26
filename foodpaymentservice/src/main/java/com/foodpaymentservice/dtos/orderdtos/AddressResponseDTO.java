package com.foodpaymentservice.dtos.orderdtos;

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
