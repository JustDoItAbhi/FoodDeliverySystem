package com.CustomerService.authorisation.registeredclient.registeredclientservice;

import com.CustomerService.authorisation.registeredclient.registeredclientdto.ClientRequestDto;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

public interface CustomizeRegeredClientService {
    RegisteredClient createRegeretedClient(ClientRequestDto dto);// method to create registered client
    boolean deleteClient(String clientId);

}
