package com.CustomerService.authorisation.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

//@JsonDeserialize
public class CustomerGrandAuthority implements GrantedAuthority {
    @JsonProperty("authority")// specified json property
    private String authorty;
    @JsonCreator
    public CustomerGrandAuthority() {
    }

    public CustomerGrandAuthority(String role) {
        System.out.println("ROLE IS HERE  :: "+ role);
        this.authorty = role;
    }

    @Override
    public String getAuthority() {
        return this.authorty;
    }
}
