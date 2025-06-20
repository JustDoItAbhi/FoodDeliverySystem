package com.CustomerService.dtos;

import com.CustomerService.models.Address;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSignUp {
    private String customerName;
    private String email;
    private String password;
    private AddressRequestDto address;
}
