package com.restaurantservice.repositories;

import com.restaurantservice.entity.VegMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VegMenuRepository extends JpaRepository<VegMenu,Long> {
    Optional<VegMenu> findByFoodItem(String food);
}
