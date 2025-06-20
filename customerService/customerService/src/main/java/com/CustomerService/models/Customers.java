package com.CustomerService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customers extends BaseModles{
    private String customerName;
    private String email;
    private String password;
    @ManyToOne
    private Address address;
}
