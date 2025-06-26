package com.foodorderservice.apicallsfromcustomer;

import com.foodorderservice.dtos.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpCallsFromServices {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public CustomerResponseDto getCustomer(String email){
        RestTemplate template=restTemplateBuilder.build();

        String url="http://localhost:9000/customer/getCustomerByEmail/"+email;

        ResponseEntity<CustomerResponseDto>response=template.getForEntity(url, CustomerResponseDto.class);
        if(response==null){
            throw new RuntimeException("customer not found "+ email);
        }
        CustomerResponseDto dto= response.getBody();
        return dto;
    }
}
