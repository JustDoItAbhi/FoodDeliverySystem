package com.restaurantservice.repositories.cartrepositories;

import com.restaurantservice.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart>findByRestName(String name);
}
