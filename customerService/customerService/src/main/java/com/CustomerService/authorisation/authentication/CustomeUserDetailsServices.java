package com.CustomerService.authorisation.authentication;

import com.CustomerService.customers.repositories.CustomerRepository;
import com.CustomerService.models.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomeUserDetailsServices implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customers>customers=customerRepository.findByEmail(username);
        if(customers.isEmpty()){
            throw new RuntimeException("USER NOT FOUNT "+ username);
        }
        return new CustomeUserDetails(customers.get());
    }
}
