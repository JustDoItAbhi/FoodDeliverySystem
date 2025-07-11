package com.CustomerService.customers.controller.roles;

import com.CustomerService.customers.dtos.CustomerLogin;
import com.CustomerService.customers.dtos.CustomerResponseDto;
import com.CustomerService.customers.dtos.CustomerSignUp;
import com.CustomerService.customers.servcies.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
private CustomerService customerService;
@PostMapping("/signup")
    public ResponseEntity<CustomerResponseDto> signUp(@RequestBody CustomerSignUp signUp){
    return ResponseEntity.ok(customerService.signupCustomer(signUp));
}
    @PostMapping("/login")
    public ResponseEntity<CustomerResponseDto> login(@RequestBody CustomerLogin login){
        return ResponseEntity.ok(customerService.login(login.getEmail(), login.getPass()));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerResponseDto>> getAll(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable ("id")Long id){
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
    @GetMapping("/getCustomerByEmail/{email}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable ("email")String email){
        return ResponseEntity.ok(customerService.getByEmail(email));
    }
}
