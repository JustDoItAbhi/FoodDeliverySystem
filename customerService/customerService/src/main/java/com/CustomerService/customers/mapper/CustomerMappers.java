package com.CustomerService.customers.mapper;

import com.CustomerService.customers.dtos.AddressResponseDTO;
import com.CustomerService.customers.dtos.CustomerResponseDto;
import com.CustomerService.models.Address;
import com.CustomerService.models.Customers;
import com.CustomerService.models.Roles;
import com.CustomerService.customers.roles.roledto.RolesResponseDto;

import java.util.ArrayList;
import java.util.List;

public class CustomerMappers {
    public static CustomerResponseDto fromEntity(Customers customers){
        CustomerResponseDto dto=new CustomerResponseDto();
        dto.setId(customers.getId());
        dto.setCustomerName(customers.getCustomerName());
        dto.setEmail(customers.getEmail());
        dto.setPassword(customers.getPassword());
        Address address= customers.getAddress();
        if(address!=null) {
            AddressResponseDTO responseDTO = new AddressResponseDTO();
            responseDTO.setBuildingNumber(customers.getAddress().getBuildingNumber());
            responseDTO.setStreet(customers.getAddress().getStreet());
            responseDTO.setPinCode(customers.getAddress().getPinCode());
            responseDTO.setCity(customers.getAddress().getCity());
            responseDTO.setCountry(customers.getAddress().getCountry());
            dto.setAddress(responseDTO);
        }else {
            dto.setAddress(null);
        }
        List<RolesResponseDto>rolesResponseDtoList=new ArrayList<>();
        for(Roles roles:customers.getRolesList()){
            rolesResponseDtoList.add(fromEntityRole(roles));
        }
        dto.setRole(rolesResponseDtoList);
        return dto;
    }
    public static RolesResponseDto fromEntityRole(Roles roles){
        RolesResponseDto responseDto=new RolesResponseDto();
        responseDto.setRolesReponse(roles.getRoles());
        return responseDto;
    }
}
