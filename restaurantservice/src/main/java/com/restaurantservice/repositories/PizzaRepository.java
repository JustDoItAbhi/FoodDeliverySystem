package com.restaurantservice.repositories;

import com.restaurantservice.entity.Pizza;
import com.restaurantservice.entity.PizzaSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza,Long> {
    List<Pizza>findByPizzaSize(PizzaSize size);
    Optional<Pizza> findByPizzaName(String pizzaName);
}
