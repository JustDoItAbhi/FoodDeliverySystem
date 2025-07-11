package com.CustomerService.customers.dtos;

import com.CustomerService.customers.roles.roledto.RolesResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerResponseDto {
    private long id;
    private String customerName;
    private String email;
    private String password;
    private AddressResponseDTO address;
    private List<RolesResponseDto>role;
}
