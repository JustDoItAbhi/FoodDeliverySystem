package com.CustomerService.servcies;

import com.CustomerService.dtos.CustomerResponseDto;
import com.CustomerService.dtos.CustomerSignUp;

import java.util.List;

public interface CustomerService {
CustomerResponseDto signupCustomer(CustomerSignUp signUp);
CustomerResponseDto login(String email,String password);
List<CustomerResponseDto> getAllCustomers();
boolean deleteCustomer(long id);
}
