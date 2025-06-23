package com.restaurantservice.repositories;

import com.restaurantservice.entity.NonVegMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NonVegMenuRepository extends JpaRepository<NonVegMenu,Long> {
    Optional<NonVegMenu> findByFoodItem(String food);
}
