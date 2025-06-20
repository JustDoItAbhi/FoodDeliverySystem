package com.CustomerService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Address extends BaseModles{
    private int buildingNumber;
    private String street;
    private String city;
    private String country;
    private long pinCode;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Customers>customers;

}
