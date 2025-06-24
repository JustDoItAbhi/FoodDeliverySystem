package com.restaurantservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Menu extends BaseModels{
    private String RestName;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<NonVegMenu>nonVegMenus;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private  List<VegMenu>vegMenus;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Pizza>pizzaList;
    @OneToOne
    private Restaurants restaurants;

}
