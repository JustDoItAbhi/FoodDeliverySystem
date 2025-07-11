package com.CustomerService.customers.servcies;

import com.CustomerService.customers.dtos.CustomerResponseDto;
import com.CustomerService.customers.dtos.CustomerSignUp;

import java.util.List;

public interface CustomerService {
CustomerResponseDto signupCustomer(CustomerSignUp signUp);
CustomerResponseDto login(String email,String password);
List<CustomerResponseDto> getAllCustomers();
boolean deleteCustomer(long id);
CustomerResponseDto getByEmail(String email);
}
