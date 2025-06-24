package com.restaurantservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pizza extends BaseModels{
    private String pizzaName;
    private PizzaSize pizzaSize;
    private double quantity;
    private double price;
    @ManyToOne
    private Menu menu;
    @Enumerated(EnumType.STRING)
    private TypeOfSpicy spicy;
}
