package com.fooddeliveryservice.fooddeliveryservice.REPOSITORY;

import com.fooddeliveryservice.fooddeliveryservice.entity.DeliveringOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveringOrder, Long> {
    Optional<DeliveringOrder>findByEmail(String email);
}
