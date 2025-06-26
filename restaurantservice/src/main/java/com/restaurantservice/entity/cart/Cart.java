package com.restaurantservice.entity.cart;

import com.restaurantservice.cart.cartdtos.Items;
import com.restaurantservice.entity.BaseModels;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Cart extends BaseModels {
    private long restaurantId;
    private String restName;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Item> itemsList;
    private double totalQuantity;
    private double totalPrice;
}
