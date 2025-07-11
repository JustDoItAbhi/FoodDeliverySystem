package com.CustomerService.authorisation.repositories;

import java.util.Optional;


import com.CustomerService.authorisation.entitys.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
    Boolean deleteByClientId(String clientId);
}