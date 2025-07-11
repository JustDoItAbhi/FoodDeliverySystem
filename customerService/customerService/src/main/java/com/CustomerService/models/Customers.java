package com.CustomerService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Customers extends BaseModles{
    private String customerName;
    private String email;
    private String password;
    @ManyToOne
    private Address address;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "customers_roles",
joinColumns =@JoinColumn (name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private List<Roles>rolesList;
}
