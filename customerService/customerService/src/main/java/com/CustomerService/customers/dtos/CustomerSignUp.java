package com.CustomerService.customers.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerSignUp {
    private String customerName;
    private String email;
    private String password;
    private List<String>rolesList;
    private AddressRequestDto address;
}
