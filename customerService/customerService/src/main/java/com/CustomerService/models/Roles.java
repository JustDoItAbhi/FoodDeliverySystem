package com.CustomerService.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Roles extends BaseModles{
private String roles;

    public Roles() {
    }

    public Roles(String roles) {
        this.roles = roles;
    }
}
