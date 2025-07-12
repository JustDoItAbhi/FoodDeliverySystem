package com.CustomerService.authorisation;

import com.CustomerService.authorisation.authentication.CustomerGrandAuthority;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public abstract class CustomeUserDetailsMixin {
    @JsonCreator
    public CustomeUserDetailsMixin(
            @JsonProperty("userName") String userName,
            @JsonProperty("password") String password,
            @JsonProperty("accountNonExpired") boolean accountNonExpired,
            @JsonProperty("accountNonLocked") boolean accountNonLocked,
            @JsonProperty("credentialsNonExpired") boolean credentialsNonExpired,
            @JsonProperty("enabled") boolean enabled,
            @JsonProperty("authorities") List<CustomerGrandAuthority> authorities
    ) {}

}
