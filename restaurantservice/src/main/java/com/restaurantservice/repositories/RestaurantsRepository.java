package com.restaurantservice.repositories;

import com.restaurantservice.entity.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants,Long> {
        Optional<Restaurants> findByrestaurantName(String name);
}
