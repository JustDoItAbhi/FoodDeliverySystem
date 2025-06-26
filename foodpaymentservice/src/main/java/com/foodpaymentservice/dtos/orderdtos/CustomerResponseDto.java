package com.foodpaymentservice.dtos.orderdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto {
    private long id;
    private String customerName;
    private String email;
    private String password;
    private AddressResponseDTO address;
}
