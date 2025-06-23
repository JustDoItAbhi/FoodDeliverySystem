package com.restaurantservice.repositories;

import com.restaurantservice.entity.Menu;
import com.restaurantservice.entity.VegMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {

}
