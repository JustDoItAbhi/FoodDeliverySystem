package com.CustomerService.customers.repositories;

import com.CustomerService.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Long> {
    Optional<Customers> findByEmail(String email);
    Optional<Customers> findByPassword(String pass);
    Optional<Customers> deleteByEmail(String pass);
}
