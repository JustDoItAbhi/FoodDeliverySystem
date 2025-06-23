package com.restaurantservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NonVegMenu extends BaseModels {
    private String foodItem;
    private double quantity;
    private double price;
    @ManyToOne
    private Menu menu;
}
