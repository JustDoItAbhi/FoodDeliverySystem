package com.restaurantservice.repositories.cartrepositories;

import com.restaurantservice.entity.cart.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
}
