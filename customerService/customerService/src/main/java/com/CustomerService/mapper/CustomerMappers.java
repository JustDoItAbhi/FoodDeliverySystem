package com.CustomerService.mapper;

import com.CustomerService.dtos.AddressResponseDTO;
import com.CustomerService.dtos.CustomerResponseDto;
import com.CustomerService.dtos.CustomerSignUp;
import com.CustomerService.models.Address;
import com.CustomerService.models.Customers;

public class CustomerMappers {
    public static CustomerResponseDto fromEntity(Customers customers){
        CustomerResponseDto dto=new CustomerResponseDto();
        dto.setId(customers.getId());
        dto.setCustomerName(customers.getCustomerName());
        dto.setEmail(customers.getEmail());
        dto.setPassword(customers.getPassword());
        AddressResponseDTO responseDTO=new AddressResponseDTO();
        responseDTO.setBuildingNumber(customers.getAddress().getBuildingNumber());
        responseDTO.setStreet(customers.getAddress().getStreet());
        responseDTO.setPinCode(customers.getAddress().getPinCode());
        responseDTO.setCity(customers.getAddress().getCity());
        responseDTO.setCountry(customers.getAddress().getCountry());
        dto.setAddress(responseDTO);
        return dto;
    }
}
