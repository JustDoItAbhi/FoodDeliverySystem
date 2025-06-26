package com.restaurantservice.entity.cart;

import com.restaurantservice.entity.BaseModels;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class Item extends BaseModels {
    private String foodName;
    private double quantity;
    @ManyToOne
    private Cart cart;
}
