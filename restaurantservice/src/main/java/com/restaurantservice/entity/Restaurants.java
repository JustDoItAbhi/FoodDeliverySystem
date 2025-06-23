package com.restaurantservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Restaurants extends BaseModels {
    private String restaurantName;
    @OneToMany(mappedBy = "restaurants",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Addresses> address;
    @OneToOne
    private Menu menu;
}
