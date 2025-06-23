package com.restaurantservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.ToOne;

import java.util.List;

@Getter
@Setter
@Entity
public class Addresses extends BaseModels {
    private int buildingNumber;
    private String street;
    private String city;
    private String country;
    private long pinCode;
    @ManyToOne
    private Restaurants restaurants;
}
