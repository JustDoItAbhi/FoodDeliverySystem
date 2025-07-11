package com.CustomerService.authorisation.authentication;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@JsonDeserialize
public class CustomerGrandAuthority implements GrantedAuthority {

    private String grandAuthorty;

    public CustomerGrandAuthority() {
    }

    public CustomerGrandAuthority(String role) {
        System.out.println("ROLE IS HERE  :: "+ role);
        this.grandAuthorty = role;
    }

    @Override
    public String getAuthority() {
        return this.grandAuthorty;
    }
}
